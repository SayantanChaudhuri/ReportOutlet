package com.sayantan.retail.vo;

import com.sayantan.retail.model.Customer;
import com.sayantan.retail.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestVO {

    private Customer customer;
    private Product product;
}
