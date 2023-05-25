/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.service.impl;

import com.alipay.rmall.base.constants.RedisConstants;
import com.alipay.rmall.base.utils.RedisCacheUtils;
import com.alipay.rmall.dal.dao.ProductBaseInfoDAO;
import com.alipay.rmall.dal.domain.ProductBaseInfoDO;
import com.alipay.rmall.dto.ProductBaseInfoDTO;
import com.alipay.rmall.service.ProductService;
import com.alipay.rmall.utils.ProductServiceUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ruitu.xr
 * @version ProductServiceImpl.java, v 0.1 2023年05月19日 11:31 ruitu.xr Exp $
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductBaseInfoDAO productBaseInfoDAO;

    @Resource
    private RedisCacheUtils redisCacheUtils;

    @Override
    public int add(ProductBaseInfoDTO productBaseInfoDTO) {
        ProductBaseInfoDO productBaseInfoDO = ProductServiceUtil.convertProductBaseInfoDTO2DO(productBaseInfoDTO);
        productBaseInfoDAO.insertSelective(productBaseInfoDO);
        return productBaseInfoDO.getId();
    }

    @Override
    public ProductBaseInfoDTO getProductById(int id) {
        //查询缓存
        String key = RedisConstants.PRODUCT_KEY_PREFIX + id;
        ProductBaseInfoDO productBaseInfoDO = redisCacheUtils.getObject(key, ProductBaseInfoDO.class);
        if (productBaseInfoDO == null) {
            synchronized (ProductServiceImpl.class) {
                productBaseInfoDO = redisCacheUtils.getObject(key, ProductBaseInfoDO.class);
                if (productBaseInfoDO == null) {
                    //缓存中没有的话，就到数据库中查
                    productBaseInfoDO = productBaseInfoDAO.selectByPrimaryKey(id);
                    if (productBaseInfoDO == null) {
                        //数据库中也没有的话，就抛一个业务异常出去
                        throw new RuntimeException(String.format("不存在id为%s的产品，如有问题，请联系技术同学", id));
                    } else {
                        //数据库中有的话，将数据回写进redis
                        redisCacheUtils.add(key, productBaseInfoDO);
                    }
                }
            }
        }

        return ProductServiceUtil.convertProductBaseInfoDO2DTO(productBaseInfoDO);
    }
}
