/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.web.utils;

import com.alipay.rmall.dto.ProductBaseInfoDTO;
import com.alipay.rmall.web.vo.ProductBaseInfoVO;
import org.springframework.beans.BeanUtils;

import java.util.Random;

/**
 * @author ruitu.xr
 * @version ProductBaseInfoConvertUtil.java, v 0.1 2023年05月17日 16:35 ruitu.xr Exp $
 */
public class ProductBaseInfoConvertUtil {

    /**
     * VO转DTO
     * @param productBaseInfoVO
     * @return
     */
    public static ProductBaseInfoDTO convertProductBaseInfoVO2DTO(ProductBaseInfoVO productBaseInfoVO) {
        ProductBaseInfoDTO productBaseInfoDTO = new ProductBaseInfoDTO();
        BeanUtils.copyProperties(productBaseInfoVO, productBaseInfoDTO);
        productBaseInfoDTO.setOperator("user:" + new Random().nextInt(9999));
        return productBaseInfoDTO;
    }

    /**
     * DTO转VO
     * @param productBaseInfoDTO
     * @return
     */
    public static ProductBaseInfoVO convertProductBaseInfoDTO2VO(ProductBaseInfoDTO productBaseInfoDTO) {
        ProductBaseInfoVO productBaseInfoVO = new ProductBaseInfoVO();
        BeanUtils.copyProperties(productBaseInfoDTO, productBaseInfoVO);
        return productBaseInfoVO;
    }
}
