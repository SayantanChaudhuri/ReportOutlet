package com.sayantan.retail.model;

import com.sayantan.retail.type.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private long userId;
    private String userName;
    private CustomerType customerType;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private boolean isActive;
    private Cart cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return userId == customer.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
