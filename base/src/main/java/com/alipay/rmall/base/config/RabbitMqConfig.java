/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author ruitu.xr
 * @version RabbitMqConfig.java, v 0.1 2023年05月25日 19:13 ruitu.xr Exp $
 */
@Configuration
@Slf4j
public class RabbitMqConfig {

    @Resource
    private CachingConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                String msgId = correlationData.getId();
                if (ack) {
                    log.info("消息成功发送到Exchange, msgId: {}", msgId);
                } else {
                    log.error("消息发送到Exchange失败，{}, case: {}", correlationData, cause);
                }
            }
        });
        //触发setReturnCallback回调必须设置mandatory=true,否则Exchange没有找到Queue就会丢弃掉消息,而不会触发回调
        rabbitTemplate.setMandatory(true);
        //消息是否从Exchange路由到Queue,注意:这是一个失败回调,只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                log.error("消息从Exchange路由到Queue失败:exchange:{},route key:{},replyCode:{},replyText:{},message:{}", returned.getExchange(),
                        returned.getRoutingKey(), returned.getReplyCode(), returned.getReplyText(), returned.getMessage());
            }
        });

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String DIRECT_QUEUE = "direct_queue";
    public static final String DIRECT_ROUTING_KEY = "direct_routing_key";

    @Bean
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE, true, false, false);
    }
    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(DIRECT_ROUTING_KEY);
    }

    /**
     * canal + rabbitmq 配置信息
     */
    public static final String CANAL_EXCHANGE = "canal_exchange";
    public static final String CANAL_QUEUE = "canal_queue";
    public static final String CANAL_ROUTING_KEY = "canal_routing_key";

    @Bean
    public DirectExchange canalDirectExchange() {
        return ExchangeBuilder.directExchange(CANAL_EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue canalDirectQueue() {
        return new Queue(CANAL_QUEUE, true, false, false);
    }
    @Bean
    public Binding canalDirectBinding() {
        return BindingBuilder.bind(canalDirectQueue()).to(canalDirectExchange()).with(CANAL_ROUTING_KEY);
    }
}
