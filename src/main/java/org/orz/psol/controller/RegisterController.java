package org.orz.psol.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.orz.psol.mapper.UserMapper;
import org.orz.psol.model.RespBean;
import org.orz.psol.model.User;
import org.orz.psol.service.UserService;
import org.orz.psol.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @PostMapping("/ck1")
    RespBean checkUsername(@RequestParam String username) {
        int count = userMapper.countUsername(username);
        if (count > 0)
            return RespBean.error("该登录名已被注册");
        else
            return RespBean.ok("");
    }

    @PostMapping("/ck2")
    RespBean checkIdNumber(@RequestParam String idNumber) {
        int count = userMapper.countId(idNumber);
        if (count > 0)
            return RespBean.error("该身份证号已被注册");
        else
            return RespBean.ok("");
    }

    @PostMapping("/ck3")
    RespBean checkTelephone(@RequestParam String telephone) {
        int count = userMapper.countPhone(telephone);
        if (count > 0)
            return RespBean.error("该手机号已被注册");
        else
            return RespBean.ok("");
    }

    @PostMapping("/do")
    RespBean register(
            @RequestParam String username, @RequestParam String password,
            @RequestParam String nickname, @RequestParam String name,
            @RequestParam String idNumber, @RequestParam String telephone){
        if (username == null || password == null || nickname == null || name == null || idNumber == null || telephone == null)
            return RespBean.error("数据不完整,注册失败!");
        if (userMapper.countId(idNumber) > 0 || userMapper.countPhone(telephone) > 0 || userMapper.countUsername(username) > 0)
            return RespBean.error("注册失败");

        String id = UUIDGenerator.getUUID();
        boolean success = userService.add(id, username, password, "ROLE_user");
        if (!success)
            return RespBean.error("注册失败");
        else {
            int count = userMapper.addPuser(id, nickname, name, idNumber, telephone);
            if (count == 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                userMapper.deleteByMap(map);
                return RespBean.error("注册失败");
            } else if (count == 1)
                return RespBean.ok("注册成功");
            else
                return RespBean.error("注册失败");
        }
    }


}
