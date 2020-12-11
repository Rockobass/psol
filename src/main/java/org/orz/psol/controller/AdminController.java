package org.orz.psol.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.orz.psol.model.Admin;
import org.orz.psol.model.RespBean;
import org.orz.psol.service.impl.AdminServiceImpl;
import org.orz.psol.service.impl.UserServiceImpl;
import org.orz.psol.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统管理模块")
@RestController
@RequestMapping("/s/admin")
public class AdminController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    AdminServiceImpl adminService;

    @ApiOperation(value = "删除管理员账号",notes = "设置activate字段为0")
    @PostMapping("/delete")
    RespBean delete(@ApiParam(value = "uuid形式", required = true) @RequestParam String id) {
        boolean success = false;
        success = userService.deleteByUUID(id);
        if (success)
            return RespBean.ok("删除成功");
        else
            return RespBean.error("删除失败");
    }

    @ApiOperation(value = "添加管理员", notes = "在user和admin表中添加")
    @PostMapping("/add")
    RespBean add(@RequestParam String username, @RequestParam String password, @ApiParam(value = "管理员名称", required = true) @RequestParam String name) {
        boolean success;
        String id = UUIDGenerator.getUUID();
        success = userService.add(id,username,password,"ROLE_admin");
        if (success){
            Admin admin = new Admin(id,name);
            adminService.save(admin);
            return RespBean.ok("添加成功");
        }
        else
            return RespBean.error("添加失败");
    }

    
}
