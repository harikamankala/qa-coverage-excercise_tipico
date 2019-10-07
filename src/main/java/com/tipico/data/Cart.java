package com.tipico.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private int id;

    private float total_price;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "items_tracking_table", joinColumns = @JoinColumn(name = "counter_id"))
    @MapKeyJoinColumn(name = "item_id", referencedColumnName = "id")
    private Map<Item, Byte> items = new HashMap<>();

    private int discount;
    private int voucherDiscount;

    public Cart() {
    }

    public Cart(float total_price, Map<Item, Byte> items, Integer discount, int voucherDiscount) {
        this.total_price = total_price;
        this.items = items;
        this.discount = discount;
        this.voucherDiscount = voucherDiscount;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public void addItem(Item item) {
        items.putIfAbsent(item, (byte)0);
        byte count = items.get(item);
        items.put(item, ++count);
        total_price += item.getPrice();
    }

    public void removeItem(Item item) {
        items.computeIfPresent(item, (it, cnt) -> {
            total_price -= it.getPrice();
            if (--cnt <= 0) {
                return null;
            }
            return cnt;
        });
    }

    public Map<Item, Byte> getItems() {
        return items;
    }

    public void setItems(Map<Item, Byte> items) {
        this.items = items;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void applyVoucher() {
        voucherDiscount += 2;
    }

    public int getVoucherDiscount() {
        return voucherDiscount;
    }

    public void setVoucherDiscount(int voucherDiscount) {
        this.voucherDiscount = voucherDiscount;
    }

    public void clearCart() {
        total_price = 0.0F;
        items.clear();
        discount = 0;
        voucherDiscount = 0;
    }

}
