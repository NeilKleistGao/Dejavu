package com.buptse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buptse.pojo.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ASJA
 * @since 2021-04-23
 */
public interface TransactionMapper extends BaseMapper<Transaction> {

    @Insert("insert into transaction (buyer_id,seller_id,car_id,deal_price,deal_time)"
            + "values(#{buyer_id},#{seller_id},#{car_id},#{deal_price},#{deal_time})")
    @Options(useGeneratedKeys = true,keyProperty = "sale_id",keyColumn = "sale_id")
    int insertTransaction(Transaction transaction);
}
