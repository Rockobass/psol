package org.orz.psol.controller;

import org.orz.psol.mapper.CartItemMapper;
import org.orz.psol.model.CartItemInfo;
import org.orz.psol.model.RespBean;
import org.orz.psol.service.CartItemService;
import org.orz.psol.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cartItem")
public class CartItemContronller {

    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    RespBean addCartItem(@RequestParam String choiceId,@RequestParam String userId,@RequestParam int number) {
        if (choiceId==null && userId == null )
            return RespBean.error("数据不完整,添加失败!");

        String productId = cartItemService.slecetProductId(choiceId);
        String storeId = cartItemService.slecetStoreId(productId);

        int i_number = cartItemService.selectNumberCartItem(choiceId, userId);

        if (i_number == 0) {
            boolean success=cartItemService.addcartItem(choiceId, storeId, productId, userId, number, 0);
            if (!success)
                return RespBean.error("加入购物车失败11！");
            else return RespBean.ok("加入了购物车！");
        } else {

            boolean success = cartItemService.updateCartItem(i_number+number,choiceId,userId);
            if (!success)
                return RespBean.error("加入购物车失败22！");
            else return RespBean.ok("加入了购物车！");
        }
    }

    @PostMapping("/delete")
    RespBean deleteCartItem(
            @RequestParam String choiceId,  @RequestParam String userId) {
        if (choiceId==null && userId == null )
            return RespBean.error("数据不完整!");
        boolean success = cartItemService.deleteCartItem(choiceId,userId);
        if (!success)
            return RespBean.error("移除购物车失败！");
        else return RespBean.ok(null,cartService.getCart(userId));

    }

    @PostMapping("/account")
    public double accountCartItem(@RequestParam String userId) {
        return cartItemService.account(userId);
    }


    @PostMapping("/update")
    RespBean reduceCartItem(@RequestParam String choiceId,@RequestParam String userId,@RequestParam int number) {
        if (choiceId==null || userId == null || number==0)
            return RespBean.error("数据不完整!");

        String productId = cartItemService.slecetProductId(choiceId);
        String storeId = cartItemService.slecetStoreId(productId);

        boolean success=cartItemService.updateCartItem(number,choiceId,userId );
        if (!success)
            return RespBean.error("更新失败！");
        else return RespBean.ok(null);

    }

}
