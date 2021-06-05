package com.buptse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buptse.mapper.TransactionMapper;
import com.buptse.pojo.Transaction;
import com.buptse.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ASJA
 * @since 2021-04-23
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {


    @Override
    public int insertTransactionAndGetId(Transaction transaction) {
        getBaseMapper().insertTransaction(transaction);
        return transaction.getSale_id();
    }
}
