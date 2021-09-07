package com.buptse.handle;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buptse.pojo.User;
import com.buptse.service.IUserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserHandle
 * @Description TODO
 * @Author ASJA
 * @Date 2021/9/5 11:42
 * @Version 1.0
 */
@Component
public class UserHandle {

    @Autowired
    IUserService userService;

    @KafkaListener(topics = "user-topic-modify-mail", groupId = "user-group")
    public void listenUserGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        User user = userService.getById(record.key());
        updateWrapper.set("mail",record.value());
        userService.update(user,updateWrapper);
        ack.acknowledge();

    }

}
