package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buptse.common.RESULT;
import com.buptse.common.util.CarStateAndCodeUtile;
import com.buptse.dto.CommonResult;
import com.buptse.dto.SaleDeleteDto;
import com.buptse.dto.TransactionQueryDto;
import com.buptse.dto.TransactionModifyDto;
import com.buptse.pojo.Car;
import com.buptse.pojo.Transaction;
import com.buptse.pojo.User;
import com.buptse.service.ICarService;
import com.buptse.service.ITokenService;
import com.buptse.service.ITransactionService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestBody TransactionModifyDto transactionModifyDto){
        UpdateWrapper<Transaction> transactionWrapper = new UpdateWrapper<>();
        transactionWrapper.eq("sale_id", transactionModifyDto.getSale_id());
        if(transactionModifyDto.getBuyer_id()!=null){transactionWrapper.set("buyer_id",
            transactionModifyDto.getBuyer_id());}
        if(transactionModifyDto.getSeller_id()!=null){transactionWrapper.set("seller_id",
            transactionModifyDto.getSale_id());}
        if(transactionModifyDto.getCar_id()!=null){transactionWrapper.set("car_id",
            transactionModifyDto.getCar_id());}
        if(transactionModifyDto.getDeal_price()!=null){transactionWrapper.set("deal_price",
            transactionModifyDto.getDeal_price());}
        if(transactionModifyDto.getDeal_time()!=null){transactionWrapper.set("deal_time",
            transactionModifyDto.getDeal_time());}
        if(transactionService.update(transactionWrapper)){
            return CommonResult.success(true);
        }
        return CommonResult.failFast(RESULT.NO_DATA, false);
    }

    @RequiresRoles({"manager"})
    @PostMapping("/transaction/sale/submit")
    public CommonResult sumbitTransaction(
            @RequestBody TransactionModifyDto transactionModifyDto){
        final Car car = carService.getById(transactionModifyDto.getCar_id());
        Transaction transaction = Transaction.builder()
                .buyer_id(transactionModifyDto.getBuyer_id())
                .seller_id(transactionModifyDto.getSeller_id())
                .car_id(transactionModifyDto.getCar_id())
                .deal_price(transactionModifyDto.getDeal_price())
                .deal_time(transactionModifyDto.getDeal_time())
                .build();
        int id = transactionService.insertTransactionAndGetId(transaction);
        car.setState(CarStateAndCodeUtile.getCodeByState("已售"));
        carService.updateById(car);
        return CommonResult.success(id);
    }

    @RequiresRoles({"manager"})
    @PostMapping("/transaction/sale/delete")
    public CommonResult deleteTransaction(
            @RequestBody SaleDeleteDto saleDeleteDto){
        final Integer sale_id = saleDeleteDto.getSale_id();
        final Transaction transaction = transactionService.getById(sale_id);
        if(transaction == null){
            return CommonResult.failFast(RESULT.NO_DATA);
        }
        final Car car = carService.getById(transaction.getCar_id());
        if(car != null) {
            car.setState(CarStateAndCodeUtile.getCodeByState("待售"));
            carService.updateById(car);
        }
        if(transactionService.removeById(saleDeleteDto.getSale_id())){
            return CommonResult.success(true);
        }
        return CommonResult.failFast(RESULT.NO_DATA,false);
    }



}
