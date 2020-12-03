package org.orz.psol.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.orz.psol.model.User;
import org.orz.psol.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "测试模块")
@RestController
public class TestController {
    @Autowired
    UserServiceImpl userService;

    @ApiOperation(value = "测试函数")
    @GetMapping("/test")
    User test(@RequestParam String username){
        System.out.println(username);
        return userService.loadByUsername("admin");
    }
}
