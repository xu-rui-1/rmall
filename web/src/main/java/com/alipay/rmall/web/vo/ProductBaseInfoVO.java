/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.rmall.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author ruitu.xr
 * @version ProductBaseInfoVO.java, v 0.1 2023年05月17日 16:31 ruitu.xr Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBaseInfoVO {

    private String productName;

    private BigDecimal productPrice;

    private String productDetail;
}
