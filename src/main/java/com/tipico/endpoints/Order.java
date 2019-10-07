package com.tipico.endpoints;

import com.tipico.data.Item;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {

    private BigDecimal originalPrice;
    private BigDecimal paid;
    private String description;
    private Integer discount;
    private Integer voucherDiscount;
    private Date orderDate;
    private List<Item> items;

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getVoucherDiscount() {
        return voucherDiscount;
    }

    public void setVoucherDiscount(Integer voucherDiscount) {
        this.voucherDiscount = voucherDiscount;
    }
}
