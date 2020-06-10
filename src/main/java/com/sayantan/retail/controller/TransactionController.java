package com.sayantan.retail.controller;

import com.sayantan.retail.model.Customer;
import com.sayantan.retail.model.Employee;
import com.sayantan.retail.model.Transaction;
import com.sayantan.retail.service.TransactionService;
import com.sayantan.retail.type.StatusType;
import com.sayantan.retail.vo.TransactionRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/transaction")
public class TransactionController extends BasicController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/test")
    public String test() {
        return "Sayantan Transaction";
    }

    @PostMapping("/createtransaction")
    public Map createTransaction(@RequestBody TransactionRequestVO transactionRequestVO) {

        log.info("createTransaction entry : {}", transactionRequestVO);

        Customer customer = transactionRequestVO.getCustomer();
        Employee salesMan = transactionRequestVO.getSalesMan();

        Transaction transaction = transactionService.createTransaction(customer, salesMan);

        return map(StatusType.SUCCESS, transaction);
    }
}
