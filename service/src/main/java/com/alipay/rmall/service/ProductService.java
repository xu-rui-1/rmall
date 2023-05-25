package com.alipay.rmall.service;

import com.alipay.rmall.dto.ProductBaseInfoDTO;

/**
 * @author ruitu.xr
 * @version ProductService.java, v 0.1 2023年05月19日 11:31 ruitu.xr Exp $
 */
public interface ProductService {

    ProductBaseInfoDTO getProductById(int id);

    /**
     * 添加产品信息
     * @param productBaseInfoDTO
     * @return
     */
    int add(ProductBaseInfoDTO productBaseInfoDTO);
}
