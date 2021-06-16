package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buptse.common.RESULT;
import com.buptse.common.util.CarStateAndCodeUtile;
import com.buptse.dto.CommonResult;
import com.buptse.dto.SaleDeleteDto;
import com.buptse.dto.TransactionQueryDto;
import com.buptse.dto.TranscationModifyDto;
import com.buptse.pojo.Car;
import com.buptse.pojo.Transaction;
import com.buptse.pojo.User;
import com.buptse.service.ICarService;
import com.buptse.service.ITokenService;
import com.buptse.service.ITransactionService;
import java.text.Format;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TransactionController
 * @Author ASJA
 * @Date 2021/6/4 16:16
 * @Version 1.0
 */

@RestController
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private ICarService carService;

    @Autowired
    private ITokenService tokenService;

    @PostMapping("/transaction/sale/query")
    public CommonResult queryTransaction(
            @RequestBody TransactionQueryDto transactionQueryDto){
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            return CommonResult.failFast(RESULT.USER_NOT_LOGIN);
        }
        User user = (User)subject.getPrincipal();
        if(!user.getUid().equals(transactionQueryDto.getBuyer())
            &&(!subject.hasRole("manager"))
            &&!user.getUid().equals(transactionQueryDto.getSeller())){
            return CommonResult.failFast(RESULT.NO_PERMISSION);
        }
        QueryWrapper<Transaction> transactionQueryWrapper  =new QueryWrapper<>();
        if(transactionQueryDto.getDate()!=null){
            final LocalDateTime date = transactionQueryDto.getDate();
            transactionQueryWrapper.apply(
                new StringBuilder()
                    .append("to_days(\'")
                    .append(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .append("\') = ")
                    .append("to_days(DATE_FORMAT(deal_time,'%Y-%m-%d'))")
                    .toString());
        }
        if(transactionQueryDto.getBuyer()!=null){transactionQueryWrapper.eq("buyer_id",transactionQueryDto.getBuyer());}
        if(transactionQueryDto.getSeller()!=null){transactionQueryWrapper.eq("seller_id",transactionQueryDto.getSeller());}
        List<Transaction> list = transactionService.list(transactionQueryWrapper);
        return CommonResult.success(list);

    }

    @RequiresRoles({"manager"})
    @PostMapping("/transaction/sale/modify")
    public CommonResult modifyTransaction(
            @RequestBody TranscationModifyDto transcationModifyDto){
        UpdateWrapper<Transaction> transactionWrapper = new UpdateWrapper<>();
        transactionWrapper.eq("sale_id",transcationModifyDto.getSale_id());
        if(transcationModifyDto.getBuyer_id()!=null){transactionWrapper.set("buyer_id",transcationModifyDto.getBuyer_id());}
        if(transcationModifyDto.getSeller_id()!=null){transactionWrapper.set("seller_id",transcationModifyDto.getSale_id());}
        if(transcationModifyDto.getCar_id()!=null){transactionWrapper.set("car_id",transcationModifyDto.getCar_id());}
        if(transcationModifyDto.getDeal_price()!=null){transactionWrapper.set("deal_price",transcationModifyDto.getDeal_price());}
        if(transcationModifyDto.getDeal_time()!=null){transactionWrapper.set("deal_time",transcationModifyDto.getDeal_time());}
        if(transactionService.update(transactionWrapper)){
            return CommonResult.success(true);
        }
        return CommonResult.failFast(RESULT.NO_DATA, false);
    }

    @RequiresRoles({"manager"})
    @PostMapping("/transaction/sale/submit")
    public CommonResult sumbitTransaction(
            @RequestBody TranscationModifyDto transcationModifyDto){
        final Car car = carService.getById(transcationModifyDto.getCar_id());
        Transaction transaction = Transaction.builder()
                .buyer_id(transcationModifyDto.getBuyer_id())
                .seller_id(transcationModifyDto.getSeller_id())
                .car_id(transcationModifyDto.getCar_id())
                .deal_price(transcationModifyDto.getDeal_price())
                .deal_time(transcationModifyDto.getDeal_time())
                .build();
        int id = transactionService.insertTransactionAndGetId(transaction);
        car.setState(CarStateAndCodeUtile.getCodeByBody("已售"));
        carService.updateById(car);
        return CommonResult.success(id);
    }

    @RequiresRoles({"manager"})
    @PostMapping("/transaction/sale/delete")
    public CommonResult deleteTransaction(
            @RequestBody SaleDeleteDto saleDeleteDto){
        final Integer sale_id = saleDeleteDto.getSale_id();
        final Transaction transaction = transactionService.getById(sale_id);
        final Car car = carService.getById(transaction.getCar_id());
        car.setState(CarStateAndCodeUtile.getCodeByBody("待售"));
        carService.updateById(car);
        if(transactionService.removeById(saleDeleteDto.getSale_id())){
            return CommonResult.success(true);
        }
        return CommonResult.failFast(RESULT.NO_DATA,false);
    }



}
