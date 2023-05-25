/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.web.controller;

import com.alipay.rmall.service.KafkaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ruitu.xr
 * @version KafkaController.java, v 0.1 2023年05月22日 18:55 ruitu.xr Exp $
 */
@RestController
@Api(tags = "Kafka 测试controller")
public class KafkaController {
    @Resource
    private KafkaService kafkaService;

    @Value("${spring.kafka.topics.biz}")
    private String topic;

    @GetMapping(value = "/add")
    @ApiOperation(value = "向kafka发送消息")
    public String sendMsg(String message) {
        try {
            kafkaService.sendMessage(new ProducerRecord<>(topic, message));
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }

        return "success";
    }
}
