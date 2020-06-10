package com.sayantan.retail.service.impl;

import com.sayantan.retail.model.*;
import com.sayantan.retail.sequence.SequenceGenerator;
import com.sayantan.retail.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private KieContainer kieContainer;

    @Override
    public Transaction createTransaction(Customer customer, Employee salesMan) {

        Transaction transaction = new Transaction()
                                    .builder()
                                    .transactionId(SequenceGenerator.getNext())
                                    .customer(customer)
                                    .salesMan(salesMan)
                                    .products(new LinkedHashSet<Product>())
                                    .discounts(new ArrayList<Discount>())
                                    .build();

        addProductToTransaction(transaction, customer);

        calculateTotalPurchaseAmount(transaction);

        calculateDiscount(transaction);

        log.info("transaction :" + transaction);

        return transaction;
    }

    private LinkedHashSet<Product> getProducts(Customer customer) {

        LinkedHashSet<Product> products = customer.getCart().getProducts();

        if(products.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        return products;
    }

    private LinkedHashSet<Product> getProducts(Transaction transaction) {

        return  getProducts(transaction.getCustomer());
    }

    private Transaction addProductToTransaction(Transaction transaction, Customer customer) {

        log.info("customer products :" + getProducts(customer));

        log.info("transaction products :" + transaction.getProducts());

        LinkedHashSet<Product> products = getProducts(transaction);

        products.addAll(getProducts(customer));

        transaction.setProducts(products);

        return transaction;
    }

    private Transaction calculateTotalPurchaseAmount(Transaction transaction) {

         double totalPrice =
                    transaction
                         .getProducts()
                         .stream()
                         .mapToDouble(product -> product.getPrice() * product.getCount())
                         .sum();

         transaction.setPurchaseAmount(totalPrice);

         return transaction;
    }

    private Transaction calculateDiscount(Transaction transaction) {

        log.info("Transaction $ : " + transaction);

        KieSession kieSession = kieContainer.newKieSession("ksession-rule");
        log.info("kieSession $ : " + kieSession);
        kieSession.insert(transaction);
        kieSession.fireAllRules();
        kieSession.dispose();
        return transaction;
    }
}
