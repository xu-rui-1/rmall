package com.alipay.rmall.dal.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author xurui
 */
public class ProductBaseInfoDO {
    /**
     *
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     *
     * @mbg.generated
     */
    private String productName;

    /**
     *
     *
     * @mbg.generated
     */
    private BigDecimal productPrice;

    /**
     *
     *
     * @mbg.generated
     */
    private String productDetail;

    /**
     *
     *
     * @mbg.generated
     */
    private String operator;

    /**
     *
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     *
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * @return the value of product_base_info.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id the value for product_base_info.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return the value of product_base_info.product_name
     *
     * @mbg.generated
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName the value for product_base_info.product_name
     *
     * @mbg.generated
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     *
     * @return the value of product_base_info.product_price
     *
     * @mbg.generated
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     *
     * @param productPrice the value for product_base_info.product_price
     *
     * @mbg.generated
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     *
     * @return the value of product_base_info.product_detail
     *
     * @mbg.generated
     */
    public String getProductDetail() {
        return productDetail;
    }

    /**
     *
     * @param productDetail the value for product_base_info.product_detail
     *
     * @mbg.generated
     */
    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    /**
     *
     * @return the value of product_base_info.operator
     *
     * @mbg.generated
     */
    public String getOperator() {
        return operator;
    }

    /**
     *
     * @param operator the value for product_base_info.operator
     *
     * @mbg.generated
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     *
     * @return the value of product_base_info.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     *
     * @param gmtCreate the value for product_base_info.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     *
     * @return the value of product_base_info.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     *
     * @param gmtModified the value for product_base_info.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * @return
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productName=").append(productName);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productDetail=").append(productDetail);
        sb.append(", operator=").append(operator);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}