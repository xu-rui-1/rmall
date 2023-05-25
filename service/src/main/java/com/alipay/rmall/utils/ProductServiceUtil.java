/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.utils;

import com.alipay.rmall.dal.domain.ProductBaseInfoDO;
import com.alipay.rmall.dto.ProductBaseInfoDTO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author ruitu.xr
 * @version ProductServiceUtil.java, v 0.1 2023年05月19日 12:04 ruitu.xr Exp $
 */
public class ProductServiceUtil {

    /**
     * DTO转DO
     * @param productBaseInfoDTO
     * @return
     */
    public static ProductBaseInfoDO convertProductBaseInfoDTO2DO(ProductBaseInfoDTO productBaseInfoDTO) {
        ProductBaseInfoDO productBaseInfoDO = new ProductBaseInfoDO();
        BeanUtils.copyProperties(productBaseInfoDTO, productBaseInfoDO);
        productBaseInfoDO.setGmtModified(new Date());
        return productBaseInfoDO;
    }

    /**
     * DO转DTO
     * @param productBaseInfoDO
     * @return
     */
    public static ProductBaseInfoDTO convertProductBaseInfoDO2DTO(ProductBaseInfoDO productBaseInfoDO) {
        ProductBaseInfoDTO productBaseInfoDTO = new ProductBaseInfoDTO();
        BeanUtils.copyProperties(productBaseInfoDO, productBaseInfoDTO);
        return productBaseInfoDTO;
    }
}
