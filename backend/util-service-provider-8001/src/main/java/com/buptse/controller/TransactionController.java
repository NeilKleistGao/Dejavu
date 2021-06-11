package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.buptse.common.RESULT;
import com.buptse.dto.CommonResult;
import com.buptse.dto.SaleDeleteDto;
import com.buptse.dto.TransactionQueryDto;
import com.buptse.dto.TranscationModifyDto;
import com.buptse.pojo.Transaction;
import com.buptse.service.ITokenService;
import com.buptse.service.ITransactionService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private ITransactionService service;

    @Autowired
    private ITokenService tokenService;

    @GetMapping("/transaction/sale/query")
    public CommonResult queryTransaction(
            @RequestBody TransactionQueryDto transactionQueryDto){

        if(tokenService.findUserById(transactionQueryDto.getUid())==null){
            return CommonResult.failFast(RESULT.USER_NOT_LOGIN);
        }
        QueryWrapper<Transaction> transactionQueryWrapper  =new QueryWrapper<>();
        if(transactionQueryDto.getBuyer()!=null){transactionQueryWrapper.eq("buyer_id",transactionQueryDto.getBuyer());}
        if(transactionQueryDto.getSeller()!=null){transactionQueryWrapper.eq("seller_id",transactionQueryDto.getSeller());}
        List<Transaction> list = service.list(transactionQueryWrapper);
        return CommonResult.success(list);

    }

    @PostMapping("/transaction/sale/modify")
    public CommonResult modifyTransaction(
            @RequestBody TranscationModifyDto transcationModifyDto){
        if(tokenService.findUserById(transcationModifyDto.getUid())==null){
            return CommonResult.failFast(RESULT.USER_NOT_LOGIN);
        }
        UpdateWrapper<Transaction> transactionWrapper = new UpdateWrapper<>();
        transactionWrapper.eq("sale_id",transcationModifyDto.getSale_id());
        if(transcationModifyDto.getBuyer_id()!=null){transactionWrapper.set("buyer_id",transcationModifyDto.getBuyer_id());}
        if(transcationModifyDto.getSeller_id()!=null){transactionWrapper.set("seller_id",transcationModifyDto.getSale_id());}
        if(transcationModifyDto.getCar_id()!=null){transactionWrapper.set("car_id",transcationModifyDto.getCar_id());}
        if(transcationModifyDto.getDeal_price()!=null){transactionWrapper.set("deal_price",transcationModifyDto.getDeal_price());}
        if(transcationModifyDto.getDeal_time()!=null){transactionWrapper.set("deal_time",transcationModifyDto.getDeal_time());}
        if(service.update(transactionWrapper)){
            return CommonResult.success(true);
        }
        return CommonResult.failFast(RESULT.NO_DATA, false);
    }


    @PostMapping("/transaction/sale/submit")
    public CommonResult sumbitTransaction(
            @RequestBody TranscationModifyDto transcationModifyDto){
        if(tokenService.findUserById(transcationModifyDto.getUid())==null){
            return CommonResult.failFast(RESULT.USER_NOT_LOGIN);
        }
        Transaction transaction = Transaction.builder()
                .buyer_id(transcationModifyDto.getBuyer_id())
                .seller_id(transcationModifyDto.getSeller_id())
                .car_id(transcationModifyDto.getCar_id())
                .deal_price(transcationModifyDto.getDeal_price())
                .deal_time(transcationModifyDto.getDeal_time())
                .build();
        int id = service.insertTransactionAndGetId(transaction);
        return CommonResult.success(id);
    }


    @PostMapping("/transaction/sale/delete")
    public CommonResult deleteTransaction(
            @RequestBody SaleDeleteDto saleDeleteDto){

        if(tokenService.findUserById(saleDeleteDto.getUid())==null){
            return CommonResult.failFast(RESULT.USER_NOT_LOGIN);
        }

        if(service.removeById(saleDeleteDto.getSale_id())){
            return CommonResult.success(true);
        }
        return CommonResult.failFast(RESULT.NO_DATA,false);
    }



}
