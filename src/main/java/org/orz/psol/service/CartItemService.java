package org.orz.psol.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiModelProperty;
import org.orz.psol.mapper.CartItemMapper;
import org.orz.psol.mapper.UserMapper;
import org.orz.psol.model.CartItem;
import org.orz.psol.model.CartItemInfo;
import org.orz.psol.model.User;
import org.orz.psol.model.dbModel.Product;
import org.orz.psol.model.dbModel.ProductChoice;
import org.orz.psol.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CartItemService extends ServiceImpl<CartItemMapper, CartItem> {
    @Autowired
    CartItemMapper cartItemMapper;
    //查找商品id
    public  String slecetProductId(String choiceId) {
        return cartItemMapper.slecetProductId(choiceId);
    }
//查找商店id
    public  String slecetStoreId(String product_id){
        return cartItemMapper.slecetStoreId(product_id);
    }
//购物车加入新物品（数量为一）
    public boolean addcartItem(String choiceId, String storeId, String productId, String userId, int number, int invalid) {
        CartItem cartItme = new CartItem(choiceId, storeId, productId,  userId,  number, invalid);
        System.out.println(cartItme.toString());
        boolean success = false;
        try {
            cartItemMapper.addCartItem(cartItme.getChoiceId(),cartItme.getStoreId(),cartItme.getProductId(),cartItme.getUserId(),cartItme.getNumber(),cartItme.getInvalid());
            success = true;
        }catch (Exception ignored) {

        }
        return success;
    }

    public int selectNumberCartItem(String choiceId, String userId){
        boolean success = false;
        int number = 0;
        try {
            number = cartItemMapper.selectNumberCartItem(choiceId,userId);
            success = true;
        }catch (Exception ignored) {

        }
        return number;
    }
//购物车更新
    public boolean updateCartItem(int number,String choiceId,String userId){
        boolean success = false;
        try {
            cartItemMapper.updataCartItem(number,choiceId,userId);
            success = true;
        }catch (Exception ignored) {

        }
        return success;
    }
//购物车删除（数量为零）
    public boolean deleteCartItem(String choiceId,  String userId){
        boolean success = false;
        int number = 0;
        try {
            success = cartItemMapper.deleteCartItem(choiceId,userId);
            success = true;
        }catch (Exception ignored) {

        }
        return success;
    }
//购物车总价
    public double account(String userId){
        List<CartItem> cartItemList = cartItemMapper.selectUserAllCartItem(userId);
        List<String> selectUserAllChoice_idCartItem = cartItemMapper.selectUserAllChoice_idCartItem(userId);
        System.out.println("qwe");
        System.out.println(selectUserAllChoice_idCartItem);

        List<ProductChoice> price = cartItemMapper.seletePrice(selectUserAllChoice_idCartItem);
        double account = 0;
        for (CartItem s : cartItemList) {
            for(ProductChoice p: price){
                if(s.getChoiceId().equals(p.getChoiceId()) )
                    account  += s.getNumber()*p.getPrice();
            }
        }
        return account;

    }

    public List<CartItemInfo> cartview(String userId){
        List<CartItem> cartItemList = cartItemMapper.selectUserAllCartItem(userId);
        List selectUserAllChoice_idCartItem = cartItemMapper.selectUserAllChoice_idCartItem(userId);
        List<ProductChoice> priceList = cartItemMapper.seletePrice(selectUserAllChoice_idCartItem);
        List<CartItemInfo> cartItemInfoList =  new LinkedList<>();
        for(int i=0 ;i<cartItemList.size();i++){

            String choiceId = cartItemList.get(i).getChoiceId();
            String product_name = cartItemMapper.slecetproductName(cartItemList.get(i).getProductId());
            String store_name =cartItemMapper.slecetStoreName(cartItemList.get(i).getStoreId());
            double price =priceList.get(i).getPrice();
            int number = cartItemList.get(i).getNumber();
            Product product = cartItemMapper.slecetproduct(cartItemList.get(i).getProductId());

            CartItemInfo cartItemInfo = new CartItemInfo(choiceId,product_name,store_name,price,number,product);
            cartItemInfoList.add(cartItemInfo);
        }
        return cartItemInfoList;
    }
}