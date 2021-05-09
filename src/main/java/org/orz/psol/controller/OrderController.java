package org.orz.psol.controller;

import org.orz.psol.model.JsonModel.CheckoutJson;
import org.orz.psol.model.RespBean;
import org.orz.psol.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/checkout1")
    RespBean checkout1(@RequestParam int num, @RequestParam String choiceId, @RequestParam String userId) {
        return orderService.checkout1(num, choiceId, userId);
    }

    @PostMapping("/checkout")
    RespBean checkout(@RequestBody CheckoutJson data) {
        return orderService.checkout(data);
    }


}
