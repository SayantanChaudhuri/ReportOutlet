package com.sayantan.retail.service.impl;

import com.sayantan.retail.model.Cart;
import com.sayantan.retail.model.Customer;
import com.sayantan.retail.model.Product;
import com.sayantan.retail.sequence.SequenceGenerator;
import com.sayantan.retail.service.CartService;
import com.sayantan.retail.util.CurrentTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Override
    public Customer addProductToUserCart(Customer customer, Product product) {

        log.info("Customer :" + customer);
        log.info("Product :" + product);

        Cart cart = getCartFromCustomer(customer);
        if(product.getCount() == 0) {
           removeProductFromUserCart(customer, product.getProductId(), product.getCount());
        } else {
           addProduct(cart, product);
           customer.setCart(cart);
        }

        log.info("Customer return :" + customer);
        return customer;
    }

    @Override
    public Customer removeProductFromUserCart(Customer customer, long productId, int newProductCount) {

        Cart cart = getCartFromCustomer(customer);

        Predicate<Product> productPredicate = new Predicate<Product>() {
            @Override
            public boolean test(Product product) {
                return product.getProductId() == productId && newProductCount == 0;
            }
        };

        boolean isProductRemoved = cart.getProducts().removeIf(productPredicate);

        if(!isProductRemoved) {
           Optional<Product> filteredProduct = cart.getProducts().stream().filter(product -> product.getProductId() == productId).findFirst();

            if(filteredProduct.isPresent()) {
                    filteredProduct.get().setCount(newProductCount);
            }
        }

        cart.setModifiedDate(CurrentTime.now());

        return customer;
    }

    @Override
    public Customer clearUserCart(Customer customer) {

        Cart cart = getCartFromCustomer(customer);

        cart.setProducts(new LinkedHashSet<>());
        cart.setModifiedDate(CurrentTime.now());

        customer.setCart(cart);

        return customer;
    }

    private Cart createEmptyCart() {
        Cart cart = new Cart();
        cart.setCartId(SequenceGenerator.getNext());
        cart.setProducts(new LinkedHashSet<>());
        cart.setModifiedDate(CurrentTime.now());

        return cart;
    }

    private Cart addProduct(Cart cart, Product product) {
        LinkedHashSet<Product> products = getProducts(cart);
        products.add(product);

        cart.setProducts(products);
        cart.setModifiedDate(CurrentTime.now());

        return cart;
    }

    private Cart getCartFromCustomer(Customer customer) {

        Cart cart = customer.getCart();

        if(cart == null)
            cart = createEmptyCart();

        return cart;
    }

    private LinkedHashSet<Product> getProducts(Cart cart) {

        log.info(cart.toString());

        LinkedHashSet<Product> products = cart.getProducts();

        return products;
    }
}
