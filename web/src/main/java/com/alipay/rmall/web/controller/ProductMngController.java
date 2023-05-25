/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.web.controller;

import com.alipay.rmall.dal.domain.ProductBaseInfoDO;
import com.alipay.rmall.dto.ProductBaseInfoDTO;
import com.alipay.rmall.service.ProductService;
import com.alipay.rmall.web.utils.ProductBaseInfoConvertUtil;
import com.alipay.rmall.web.vo.ProductBaseInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ruitu.xr
 * @version ProductMngController.java, v 0.1 2023年05月18日 21:14 ruitu.xr Exp $
 */
@RestController
@Api(tags = "产品管理时controller")
public class ProductMngController {

    @Resource
    private ProductService productService;

    @GetMapping("/queryProduct")
    @ApiOperation(value = "数据库查询")
    public ProductBaseInfoVO queryProduct(int id) {
        ProductBaseInfoDTO productBaseInfoDTO = productService.getProductById(id);
        return ProductBaseInfoConvertUtil.convertProductBaseInfoDTO2VO(productBaseInfoDTO);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "插入数据")
    public int add(@RequestBody ProductBaseInfoVO productBaseInfoVO) {
        ProductBaseInfoDTO productBaseInfoDTO = ProductBaseInfoConvertUtil.convertProductBaseInfoVO2DTO(productBaseInfoVO);
        return productService.add(productBaseInfoDTO);
    }
}
