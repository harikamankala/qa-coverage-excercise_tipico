package com.tipico.data;

import com.tipico.OnlineShop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = OnlineShop.class)
public class PersistenceTest {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void shouldPersistItem() {
        Item item = new Item(999, "item_1", 1.0F);

        Item persistent = itemRepository.save(item);

        assertThat(itemRepository.findById(persistent.getId())).isNotNull();
    }

    @Test
    public void shouldFetchDefaultCart() {
        assertThat(cartRepository.findUserCart()).isNotNull();
    }

    @Test
    public void shouldUpdateUserCartWithItems() {
        Cart userCard = cartRepository.findUserCart();
        Item item = new Item(1000, "item_1", 1.0F);
        byte count = (byte) 123;
        userCard.setItems(map(itemRepository.save(item), count));

        cartRepository.save(userCard);

        Item another = new Item(1001,"item_2", 2.0F);
        Cart updated = cartRepository.findUserCart();
        updated.addItem(itemRepository.save(another));
        cartRepository.save(updated);

        assertThat(cartRepository.findUserCart().getItems().keySet()).containsExactlyInAnyOrder(item, another);
        assertThat(cartRepository.findUserCart().getItems().values()).containsExactlyInAnyOrder(count, (byte)1);
    }

    private static Map<Item, Byte> map(Item item, byte count) {
        Map<Item, Byte> map = new HashMap<>();
        map.put(item, count);
        return map;
    }

}