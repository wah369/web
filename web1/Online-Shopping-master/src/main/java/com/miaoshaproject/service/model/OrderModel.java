package com.miaoshaproject.service.model;


import java.math.BigDecimal;

//用户下单的用户模型
public class OrderModel {

    //交易号
    private String id;

    //用户ID
    private Integer userId;

    //商品ID
    private Integer itemId;

    //数量
    private Integer amount;

    //购买金额 若promoId不为空则为秒杀价格
    private BigDecimal orderPrice;

    //购买商品的单价 若promoId不为空则为秒杀价格
    private BigDecimal itemPrice;

    //若非空则则为秒杀商品方式下单
    private Integer promoId;


    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
