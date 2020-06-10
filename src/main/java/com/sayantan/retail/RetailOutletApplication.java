package com.sayantan.retail;

import com.sayantan.retail.model.Customer;
import com.sayantan.retail.model.Employee;
import com.sayantan.retail.model.Product;
import com.sayantan.retail.sequence.SequenceGenerator;
import com.sayantan.retail.service.impl.CartServiceImpl;
import com.sayantan.retail.service.impl.TransactionServiceImpl;
import com.sayantan.retail.type.CustomerType;
import com.sayantan.retail.util.CurrentTime;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RetailOutletApplication {

	@Autowired
	TransactionServiceImpl transactionService;

	@Autowired
	CartServiceImpl cartService;

	public static void main(String[] args) {
		SpringApplication.run(RetailOutletApplication.class, args);
	}

	@Bean
	public KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}


	public void run(String... args) throws Exception {

		Customer customer = new Customer()
						.builder()
						.userId(SequenceGenerator.getNext())
						.customerType(CustomerType.REGULAR)
						.createdDate(CurrentTime.now())
						.modifiedDate(CurrentTime.now())
						.userName("Sayantan")
						.build();

		System.out.println(customer);

		Employee salesMan = new Employee()
								.builder()
								.employeeId(SequenceGenerator.getNext())
								.employeeName("Riju")
								.createdDate(CurrentTime.now())
								.build();

		System.out.println(salesMan);

		Product iphoneSE = new Product()
								.builder()
								.productId(SequenceGenerator.getNext())
								.productName("IPhone SE")
								.brand("Apple")
								.category("Mobiles")
								.count(1)
								.price(15000)
								.build();

		Product iphone11 = new Product()
								.builder()
								.productId(SequenceGenerator.getNext())
								.productName("IPhone 11")
								.brand("Apple")
								.category("Mobiles")
								.count(0)
								.price(68300)
								.build();

		customer = cartService.addProductToUserCart(customer, iphoneSE);

		System.out.println(customer);

		customer = cartService.addProductToUserCart(customer, iphone11);

		System.out.println(customer);

		transactionService.createTransaction(customer, salesMan);
	}
}
