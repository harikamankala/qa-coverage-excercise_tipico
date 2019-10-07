package com.tipico.endpoints;

import com.tipico.data.Cart;
import com.tipico.data.CartRepository;
import com.tipico.data.Item;
import com.tipico.data.ItemRepository;
import java.math.RoundingMode;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class Controllers {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping(path = "/items")
    public List<ItemDTO> allItems() {
        return itemRepository.findAll().stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toList());
    }

    @PutMapping(path = "/cart/items/{id}")
    public ResponseEntity<Void> putItemIntoTheCart(@PathVariable(name = "id") int itemId) {
        if (!itemRepository.existsById(itemId)) {
            return ResponseEntity.notFound().build();
        }

        Cart userCart = cartRepository.findUserCart();
        userCart.addItem(itemRepository.findById(itemId).orElseThrow(IllegalStateException::new));
        cartRepository.save(userCart);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/cart/items/{id}")
    public ResponseEntity<Void> removeItemFromTheCart(@PathVariable(name = "id") int itemId) {
        if (!itemRepository.existsById(itemId)) {
            return ResponseEntity.notFound().build();
        }

        Cart userCart = cartRepository.findUserCart();
        userCart.removeItem(itemRepository.findById(itemId).orElseThrow(IllegalStateException::new));
        cartRepository.save(userCart);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/cart")
    public CartDTO viewCart() {
        Cart userCart = cartRepository.findUserCart();
        return new CartDTO(userCart, calculateDiscount(userCart.getTotal_price()));
    }

    @PutMapping(path = "/cart/voucher/{id}")
    public ResponseEntity<Void> addVoucherToTheCart(@PathVariable(name = "id") String voucherId) {
        try {
            Cart userCart = cartRepository.findUserCart();

            for (int i = 0; i < voucherId.length() - 1; i++) {
                if (!Character.isDigit(voucherId.charAt(i))) {
                    return ResponseEntity.badRequest().build();
                }
            }

            Integer.parseInt(voucherId);
            userCart.applyVoucher();
            cartRepository.save(userCart);

            return ResponseEntity.ok().build();
        } catch (Throwable e) {
            if (e instanceof OutOfMemoryError) {
                // TODO: investigate production issue
                e.printStackTrace();
            }
            throw new IllegalStateException("something went wrong!", e);
        }
    }

    @DeleteMapping(path = "/cart/voucher")
    public void removeVoucher() {
        Cart userCart = cartRepository.findUserCart();
        userCart.setVoucherDiscount(0);
        cartRepository.save(userCart);
    }

    @PostMapping(path = "/order")
    public Order postAnOrder() {
        Cart userCart = cartRepository.findUserCart();
        Order order = new Order();

        calculatePricesForOrder(userCart, order);

        if (order.getPaid().compareTo(BigDecimal.ZERO) >= 0) {
            order.setDescription("You've paid " + order.getPaid() + "$");
        } else {
            order.setDescription("We've reimbursed you " + order.getPaid() + "$");
        }

        order.setItems(new ArrayList<>(userCart.getItems().keySet()));
        order.setOrderDate(new Date());

        userCart.clearCart();
        cartRepository.save(userCart);

        return order;
    }

    private static void calculatePricesForOrder(Cart userCart, Order order) {
        order.setPaid(BigDecimal.valueOf(userCart.getTotal_price()));
        order.setOriginalPrice(BigDecimal.valueOf(userCart.getTotal_price()).setScale(2, RoundingMode.DOWN));


        int discount = calculateDiscount(userCart.getTotal_price());
        order.setDiscount(discount);
        order.setVoucherDiscount(userCart.getVoucherDiscount());

        BigDecimal percent = BigDecimal.valueOf(order.getDiscount()).divide(BigDecimal.valueOf(100));
        order.setPaid(order.getPaid().subtract(order.getPaid().multiply(percent)).setScale(2, RoundingMode.DOWN));
    }

    private static int calculateDiscount(float total_price) {
        int discount = 0;
        if (total_price > 101F && total_price <= 500F) {
            discount = 5;
        } else if (total_price > 501F && total_price <= 1000F) {
            discount = 10;
        }
        return discount;
    }

    private ItemDTO mapToItemDto(Item i) {
        return new ItemDTO(i.getId(), i.getName(), new BigDecimal(i.getPrice()).setScale(2, RoundingMode.DOWN));
    }
}
