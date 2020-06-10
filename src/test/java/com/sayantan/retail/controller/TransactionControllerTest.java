package com.sayantan.retail.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayantan.retail.model.Customer;
import com.sayantan.retail.model.Employee;
import com.sayantan.retail.model.Product;
import com.sayantan.retail.sequence.SequenceGenerator;
import com.sayantan.retail.service.TransactionService;
import com.sayantan.retail.type.CustomerType;
import com.sayantan.retail.util.CurrentTime;
import com.sayantan.retail.vo.CartRequestVO;
import com.sayantan.retail.vo.TransactionRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionService transactionService;

    final RestTemplate restTemplate = new RestTemplate();

    private Customer customer;
    private Employee salesMan;
    private Product product;
    private HttpEntity<String> entity;
    private String url;
    private CartRequestVO cartRequestVO;
    private TransactionRequestVO transactionRequestVO;
    private MockRestServiceServer mockServer;
    private String transactionUrl = "http://localhost:8080/api/transaction/createtransaction";
    private String cartUrl = "http://localhost:8080/api/cart/addproducttousercart";
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {

        objectMapper = new ObjectMapper();

        customer = new Customer()
                .builder()
                .userId(SequenceGenerator.getNext())
                .createdDate(CurrentTime.now())
                .modifiedDate(CurrentTime.now())
                .userName("Sayantan")
                .build();

        salesMan = new Employee()
                .builder()
                .employeeId(SequenceGenerator.getNext())
                .employeeName("Riju")
                .createdDate(CurrentTime.now())
                .build();

        product = new Product()
                .builder()
                .productId(SequenceGenerator.getNext())
                .productName("IPhone SE")
                .brand("Apple")
                .description("Apple IPhone SE Mobile Phone")
                .category("Mobiles")
                .count(1)
                .build();

        cartRequestVO = new CartRequestVO();
        transactionRequestVO = new TransactionRequestVO();
    }


    @Test
    public void testRegularWith5000() throws Exception {

        prepareRegularWith5000();

        String request = objectMapper.writeValueAsString(transactionRequestVO);

        mockMvc.perform(post(transactionUrl)
                .content(request)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.billingAmount", is(5000.00)));
    }


    @Test
    public void testRegularWith10000() throws Exception {

        prepareRegularWith10000();

        String request = objectMapper.writeValueAsString(transactionRequestVO);

        mockMvc.perform(post(transactionUrl)
                .content(request)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.billingAmount", is(9500.00)));
    }

    @Test
    public void testRegularWith15000() throws Exception {

        prepareRegularWith15000();

        String request = objectMapper.writeValueAsString(transactionRequestVO);

        mockMvc.perform(post(transactionUrl)
                .content(request)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.billingAmount", is(13500.00)));
    }


    @Test
    public void testPremiumWith4000() throws Exception {

        preparePremiumWith4000();

        String request = objectMapper.writeValueAsString(transactionRequestVO);

        mockMvc.perform(post(transactionUrl)
                .content(request)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.billingAmount", is(3600.00)));
    }

    @Test
    public void testPremiumWith8000() throws Exception {

        preparePremiumWith8000();

        String request = objectMapper.writeValueAsString(transactionRequestVO);

        mockMvc.perform(post(transactionUrl)
                .content(request)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.billingAmount", is(7000.00)));
    }

    @Test
    public void testPremiumWith12000() throws Exception {

        preparePremiumWith12000();

        String request = objectMapper.writeValueAsString(transactionRequestVO);

        mockMvc.perform(post(transactionUrl)
                .content(request)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.billingAmount", is(10200.00)));
    }

    @Test
    public void testPremiumWith20000() throws Exception {

        preparePremiumWith20000();

        String request = objectMapper.writeValueAsString(transactionRequestVO);

        mockMvc.perform(post(transactionUrl)
                .content(request)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.billingAmount", is(15800.00)));
    }

    private void prepareRegularWith5000() throws JsonProcessingException {

        customer.setCustomerType(CustomerType.REGULAR);
        product.setPrice(5000);

        cartRequestVO.setCustomer(customer);
        cartRequestVO.setProduct(product);

        log.info(cartRequestVO.toString());

        customer = restTemplate.postForObject(cartUrl, cartRequestVO, Customer.class);

        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customer));

        transactionRequestVO.setCustomer(customer);
        transactionRequestVO.setSalesMan(salesMan);
    }

    private void prepareRegularWith10000() {

        customer.setCustomerType(CustomerType.REGULAR);
        product.setPrice(10000);

        cartRequestVO.setCustomer(customer);
        cartRequestVO.setProduct(product);

        customer = restTemplate.postForObject(cartUrl, cartRequestVO, Customer.class);

        log.info(customer.toString());

        transactionRequestVO.setCustomer(customer);
        transactionRequestVO.setSalesMan(salesMan);
    }

    private void prepareRegularWith15000() {

        customer.setCustomerType(CustomerType.REGULAR);
        product.setPrice(15000);

        cartRequestVO.setCustomer(customer);
        cartRequestVO.setProduct(product);

        customer = restTemplate.postForObject(cartUrl, cartRequestVO, Customer.class);

        log.info(customer.toString());

        transactionRequestVO.setCustomer(customer);
        transactionRequestVO.setSalesMan(salesMan);
    }

    private void preparePremiumWith4000() {

        customer.setCustomerType(CustomerType.PREMIUM);
        product.setPrice(4000);

        cartRequestVO.setCustomer(customer);
        cartRequestVO.setProduct(product);

        customer = restTemplate.postForObject(cartUrl, cartRequestVO, Customer.class);

        log.info(customer.toString());

        transactionRequestVO.setCustomer(customer);
        transactionRequestVO.setSalesMan(salesMan);
    }

    private void preparePremiumWith8000() {

        customer.setCustomerType(CustomerType.PREMIUM);
        product.setPrice(8000);

        cartRequestVO.setCustomer(customer);
        cartRequestVO.setProduct(product);

        customer = restTemplate.postForObject(cartUrl, cartRequestVO, Customer.class);

        log.info(customer.toString());

        transactionRequestVO.setCustomer(customer);
        transactionRequestVO.setSalesMan(salesMan);
    }

    private void preparePremiumWith12000() {

        customer.setCustomerType(CustomerType.PREMIUM);
        product.setPrice(12000);

        cartRequestVO.setCustomer(customer);
        cartRequestVO.setProduct(product);

        customer = restTemplate.postForObject(cartUrl, cartRequestVO, Customer.class);

        log.info(customer.toString());

        transactionRequestVO.setCustomer(customer);
        transactionRequestVO.setSalesMan(salesMan);
    }

    private void preparePremiumWith20000() {

        customer.setCustomerType(CustomerType.PREMIUM);
        product.setPrice(20000);

        cartRequestVO.setCustomer(customer);
        cartRequestVO.setProduct(product);

        customer = restTemplate.postForObject(cartUrl, cartRequestVO, Customer.class);

        log.info(customer.toString());

        transactionRequestVO.setCustomer(customer);
        transactionRequestVO.setSalesMan(salesMan);
    }
}
