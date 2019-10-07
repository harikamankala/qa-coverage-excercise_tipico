package com.tipico.endpoints;

import com.tipico.data.Cart;
import com.tipico.data.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class CartDTO {

    private float total_price;

    private float price_after_discount;

    private List<Tuple> items;

    private Integer discount;

    private Integer voucherDiscount;

    public CartDTO() {
    }

    public CartDTO(Cart cart, int discount) {
        this.total_price = cart.getTotal_price();
        this.items = cart.getItems().entrySet()
                .stream()
                .map(e -> new Tuple(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        this.discount = discount;
        this.voucherDiscount = cart.getVoucherDiscount();
        this.price_after_discount = calculateDiscountedPrice(total_price, this.discount, voucherDiscount);
    }

    private static float calculateDiscountedPrice(float total_price, int discount, int voucherDiscount) {
        if (discount == 0.0f && voucherDiscount == 0.0f) {
            return total_price;
        }
        BigDecimal percent = BigDecimal.valueOf(discount + voucherDiscount).divide(BigDecimal.valueOf(100));
        BigDecimal totalPrice = BigDecimal.valueOf(total_price);
        return totalPrice.subtract(totalPrice.multiply(percent)).setScale(2, RoundingMode.DOWN).floatValue();
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public List<Tuple> getItems() {
        return items;
    }

    public void setItems(List<Tuple> items) {
        this.items = items;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public float getPrice_after_discount() {
        return price_after_discount;
    }

    public Integer getVoucherDiscount() {
        return voucherDiscount;
    }

    public static class Tuple {
        private Item item;
        private int count;

        public Tuple() {
        }

        public Tuple(Item item, int count) {
            this.item = item;
            this.count = count;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

}
