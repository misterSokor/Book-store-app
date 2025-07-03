package com.store.litflix.repository.cart;

import com.store.litflix.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByIdAndShoppingCartId(Long cartItemId, Long shoppingCartId);
}
