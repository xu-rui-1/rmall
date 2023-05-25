/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.model;

import lombok.Data;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ruitu.xr
 * @version RetryRecord.java, v 0.1 2023年05月22日 17:52 ruitu.xr Exp $
 */
@Data
public class RetryRecord {
    public static final String KEY_RETRY_TIMES = "retryTimes";

    private String key;
    private String value;

    private Integer retryTimes;
    private String topic;
    private Long nextTime;

    public ProducerRecord parse() {
        Integer partition = null;
        Long timestamp = System.currentTimeMillis();
        List<Header> headers = new ArrayList<>();
        ByteBuffer retryTimesBuffer = ByteBuffer.allocate(4);
        retryTimesBuffer.putInt(retryTimes);
        retryTimesBuffer.flip();
        headers.add(new RecordHeader(RetryRecord.KEY_RETRY_TIMES, retryTimesBuffer.array()));

        ProducerRecord sendRecord = new ProducerRecord(
                topic, partition, timestamp, key, value, headers);
        return sendRecord;
    }
}
