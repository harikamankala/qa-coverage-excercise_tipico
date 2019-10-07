package com.tipico.endpoints;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemDTO {

    private Integer id;

    private String name;

    private BigDecimal price;

    public ItemDTO() {
    }

    public ItemDTO(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return Objects.equals(itemDTO.price, price) &&
                Objects.equals(name, itemDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
