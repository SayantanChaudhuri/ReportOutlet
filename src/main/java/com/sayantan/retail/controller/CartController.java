package com.sayantan.retail.controller;

import com.sayantan.retail.model.Customer;
import com.sayantan.retail.service.CartService;
import com.sayantan.retail.type.StatusType;
import com.sayantan.retail.vo.CartRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/cart")
public class CartController extends BasicController {

    @Autowired
    CartService cartService;

    @GetMapping("/test")
    public String test() {
        return "Sayantan";
    }

    @PostMapping("/addproducttousercart")
    public Customer addProductToUserCart(@RequestBody CartRequestVO cartRequestVO) {
        log.info("addProductToUserCart entry : {}", cartRequestVO);
        return cartService.addProductToUserCart(cartRequestVO.getCustomer(), cartRequestVO.getProduct());
    }

    @PostMapping("/removeproducttousercart")
    public Map removeProductFromUserCart(@RequestBody CartRequestVO cartRequestVO) {
        log.info("removeProductFromUserCart entry : {}", cartRequestVO);
        return map(StatusType.SUCCESS, cartService.removeProductFromUserCart(cartRequestVO.getCustomer(), cartRequestVO.getProduct().getProductId(), cartRequestVO.getProduct().getCount()));
    }

    @PostMapping("/clearusercart")
    public Map clearUserCart(@RequestBody CartRequestVO cartRequestVO) {
        log.info("clearUserCart entry : {}", cartRequestVO);
        return map(StatusType.SUCCESS, cartService.clearUserCart(cartRequestVO.getCustomer()));
    }
}
