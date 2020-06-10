package com.sayantan.retail.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Objects;

@Data()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Cart {

    private long cartId;
    private LinkedHashSet<Product> products;
    private Timestamp modifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return cartId == cart.cartId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId);
    }
}
