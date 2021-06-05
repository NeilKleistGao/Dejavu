package com.buptse.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.buptse.common.RESULT;
import com.buptse.dto.CommonResult;
import com.buptse.pojo.Transaction;
import com.buptse.service.ITransactionService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * TODO 增加token 验证
     * @param uid
     * @param buyerId
     * @param sellerId
     * @return
     */
    @GetMapping("/transaction/sale/query")
    public CommonResult queryTransaction(
            @RequestParam("uid") Integer uid,
            @RequestParam(value = "buyer",required = false) Integer buyerId,
            @RequestParam(value = "seller",required = false) Integer sellerId){
        QueryWrapper<Transaction> transactionQueryWrapper  =new QueryWrapper<>();
        if(buyerId!=null){transactionQueryWrapper.eq("buyer_id",buyerId);}
        if(sellerId!=null){transactionQueryWrapper.eq("seller_id",sellerId);}
        List<Transaction> list = service.list(transactionQueryWrapper);
        return CommonResult.success(list);

    }

    @PostMapping("/transaction/sale/modify")
    public CommonResult modifyTransaction(
            @RequestParam("uid") Integer uid,
            @RequestParam("sale_id") Integer saleId,
            @RequestParam(value = "buyer_id",required = false) Integer buyerId,
            @RequestParam(value = "seller_id",required = false) Integer sellerId,
            @RequestParam(value = "car_id",required = false) Integer carId,
            @RequestParam(value = "deal_price",required = false) Double dealPrice,
            @RequestParam(value = "deal_date",required = false)LocalDateTime dealDate){
        UpdateWrapper<Transaction> transactionWrapper = new UpdateWrapper<>();
        transactionWrapper.eq("sale_id",saleId);
        if(buyerId!=null){transactionWrapper.set("buyer_id",buyerId);}
        if(sellerId!=null){transactionWrapper.set("seller_id",sellerId);}
        if(carId!=null){transactionWrapper.set("car_id",carId);}
        if(dealPrice!=null){transactionWrapper.set("deal_price",dealPrice);}
        if(dealDate!=null){transactionWrapper.set("deal_time",dealDate);}
        if(service.update(transactionWrapper)){
            return CommonResult.success(true);
        }
        return CommonResult.failFast(RESULT.NO_DATA, false);
    }


    @PostMapping("/transaction/sale/submit")
    public CommonResult sumbitTransaction(
            @RequestParam("uid") Integer uid,
            @RequestParam("sale_id") Integer saleId,
            @RequestParam(value = "buyer_id") Integer buyerId,
            @RequestParam(value = "seller_id") Integer sellerId,
            @RequestParam(value = "car_id") Integer carId,
            @RequestParam(value = "deal_price") BigDecimal dealPrice,
            @RequestParam(value = "deal_date")LocalDateTime dealDate){
        Transaction transaction = Transaction.builder()
                .buyer_id(buyerId)
                .seller_id(sellerId)
                .car_id(carId)
                .deal_price(dealPrice)
                .deal_time(dealDate)
                .build();
        int id = service.insertTransactionAndGetId(transaction);
        return CommonResult.success(id);
    }


    @PostMapping("/transaction/sale/delete")
    public CommonResult deleteTransaction(
            @RequestParam("uid") Integer uid,
            @RequestParam("sale_id") Integer saleId){
        if(service.removeById(uid)){
            return CommonResult.success(true);
        }
        return CommonResult.failFast(RESULT.NO_DATA,false);
    }

}
