/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.web.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.rmall.base.config.RabbitMqConfig;
import com.alipay.rmall.dto.ProductBaseInfoDTO;
import com.alipay.rmall.web.utils.ProductBaseInfoConvertUtil;
import com.alipay.rmall.web.vo.ProductBaseInfoVO;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author ruitu.xr
 * @version RabbitController.java, v 0.1 2023年05月25日 21:03 ruitu.xr Exp $
 */
@RestController
@Api(tags = "Rabbit消息发送测试接口")
public class RabbitController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody ProductBaseInfoVO productBaseInfoVO) {
        ProductBaseInfoDTO productBaseInfoDTO = ProductBaseInfoConvertUtil.convertProductBaseInfoVO2DTO(productBaseInfoVO);
        String msgId = UUID.randomUUID().toString().replaceAll("-", "");
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.DIRECT_ROUTING_KEY, JSON.toJSONString(productBaseInfoDTO), new CorrelationData(msgId));
    }
}
