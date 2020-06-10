package com.sayantan.retail.service;

import com.sayantan.retail.model.Product;
import com.sayantan.retail.model.Customer;

public interface CartService {
    Customer addProductToUserCart(Customer customer, Product product);
    Customer removeProductFromUserCart(Customer customer, long productId, int newProductCount);

    Customer clearUserCart(Customer customer);
}
