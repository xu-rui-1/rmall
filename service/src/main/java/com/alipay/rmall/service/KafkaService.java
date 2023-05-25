package com.alipay.rmall.service;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.ExecutionException;

/**
 * @author ruitu.xr
 * @version KafkaService.java, v 0.1 2023年05月22日 17:43 ruitu.xr Exp $
 */
public interface KafkaService {
    String sendMessage(ProducerRecord<String, String> record) throws ExecutionException, InterruptedException;
}
