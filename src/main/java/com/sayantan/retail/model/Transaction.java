package com.sayantan.retail.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    private long transactionId;
    private Customer customer;
    private LinkedHashSet<Product> products;
    private Employee salesMan;
    private double purchaseAmount;
    private List<Discount> discounts;
    private double totalDiscount;
    private double billingAmount;
    private Timestamp createdTime;
    private double slabBaseAmount;
    private double slabMaxAmount;
    private double slabDiscountPercentage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    public void addDiscountDetails(Discount discount) {
        this.discounts.add(discount);
    }
}
