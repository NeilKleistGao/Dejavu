package com.buptse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buptse.pojo.Transaction;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ASJA
 * @since 2021-04-23
 */
public interface ITransactionService extends IService<Transaction> {

    int insertTransactionAndGetId(Transaction transaction);
}
