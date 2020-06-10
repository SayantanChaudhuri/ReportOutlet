package com.sayantan.retail.service;

import com.sayantan.retail.model.Customer;
import com.sayantan.retail.model.Employee;
import com.sayantan.retail.model.Transaction;

public interface TransactionService {
    Transaction createTransaction(Customer customer, Employee salesMan);
}
