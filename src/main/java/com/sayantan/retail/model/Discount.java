package com.sayantan.retail.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discount {
    private String description;
    private double eligibleAmount;
    private double discountPercentage;
    private double discountedAmount;
}
