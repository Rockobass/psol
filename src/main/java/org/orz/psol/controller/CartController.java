package org.orz.psol.controller;

import org.orz.psol.model.pageModel.CartVO;
import org.orz.psol.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/getcart")
    CartVO getCart(@RequestParam String userId) {
        return cartService.getCart(userId);
    }
}
