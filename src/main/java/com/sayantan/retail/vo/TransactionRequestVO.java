package com.sayantan.retail.vo;

import com.sayantan.retail.model.Customer;
import com.sayantan.retail.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestVO {

    private Customer customer;
    private Employee salesMan;
}
