package org.orz.psol;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.orz.psol.mapper.CartItemMapper;
import org.orz.psol.mapper.UserMapper;
import org.orz.psol.mapper.dbMapper.ProductMapper;
import org.orz.psol.model.JsonModel.CartItemGroup;
import org.orz.psol.model.User;
import org.orz.psol.model.dbModel.Product;
import org.orz.psol.service.AdminServiceImpl;
import org.orz.psol.service.CartService;
import org.orz.psol.service.UserService;
import org.orz.psol.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FuncTest {
    @Autowired
    UserService userService;
    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    UserMapper userMapper;

    @Test
    void uuid(){
        String id = UUIDGenerator.getUUID();
        System.out.println(id);
    }

    @Test
    void delete() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id","1");
        wrapper.eq("role","ROlE_admin");
        User user = userService.getOne(wrapper);
        if (user != null)
            user.setActivated(false);
            userService.updateById(user);
    }

    @Test
    void add() {
        try {
            User user = new User("2","213","123","123",true);
            userMapper.insert(user);

        }catch (Exception e) {
            System.out.println("????????");
        }



    }

    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    ProductMapper productMapper;

    @Test
    void te() {
        List<Product> products = productMapper.selectList(new QueryWrapper<Product>().select("id","cover_img"));

        for (Product p : products) {
            if (p.getCoverImg().contains("/pics")) {
                System.out.println(p.getCoverImg());
                String s = p.getCoverImg().replaceFirst("/pics","");
                System.out.println(s);
                p.setCoverImg(s);
                productMapper.updateById(p);
            }
        }
    }


}
