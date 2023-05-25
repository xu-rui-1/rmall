/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ruitu.xr
 * @version RabbitMqConfig.java, v 0.1 2023年05月25日 19:13 ruitu.xr Exp $
 */
@Configuration
@Slf4j
public class RabbitMqConfig {
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
