package org.orz.psol;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.orz.psol.mapper.UserMapper;
import org.orz.psol.model.User;
import org.orz.psol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class PsolApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;
    /**
     * 使用insert方法， 接收一个对象作为参数
     */
    @Test
    void test() {
        User user = new User();
        user.setUsername("user1");
        user.setRole("ROLE_user");
        userMapper.insert(user);
    }

    /**
     * selectByMap方法，传入一个ColumnMap，返回值为list数组
     */
    @Test
    void test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("username","admin");
        List<User> users;
        users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    /**
     * selectById和selectByIds，通过Id查询，返回单个和多个对象
     */
    @Test
    void test2() {
        User user = userMapper.selectById(1);
        System.out.println(user);
        List<Integer> ids = new ArrayList<>();
        ids.add(2);
        ids.add(3);
        List<User> users = userMapper.selectBatchIds(ids);
        System.out.println(users);
    }

    /**
     * selectList 和 selectOne，返回多条和单条，通过QueryWrapper查询
     */
    @Test
    void test3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        /*
         * eq 等于 =
         * ne 不等于 <>
         * gt 大于 >
         * like like("name", "王")--->name like '%王%'
         * isNull isNull("name")
         * 更多见 https://mybatis.plus/guide/wrapper.html#abstractwrapper
         */
        wrapper.eq("username", "admin");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    /**
     * selectCount方法，用QueryWrapper查询，返回符合条件的记录条数
     */
    @Test
    void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "admin");
        Integer n = userMapper.selectCount(wrapper);
        System.out.println(n);
    }


    /**
     * update方法批量更新
     * wrapper中存储需要更改的记录的筛选条件
     * 新建一个对象，对象中设置需要更改的字段的值
     */
    @Test
    void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("activate", false);
        User user = new User();
        user.setActivated(true);
        userMapper.update(user,wrapper);
    }

    /**
     * update 方法更新单条记录
     */
    @Test
    void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "user1");
        User user = userMapper.selectOne(wrapper);
        userMapper.updateById(user);
    }

    /**
     * easy huh
     */
    @Test
    void test7() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "user1");
        userMapper.delete(wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("username","admin");
        userMapper.deleteByMap(map);
        userMapper.deleteById(1);
    }

    @Test
    void test8() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("activate", false);

        // 查询单条
        User user = userService.getOne(wrapper);
        User user1 = userService.getById(1);
        //查询多条
        List<Map<String, Object>> list = userService.listMaps();
        List<Map<String, Object>> list1 = userService.listMaps(wrapper);
        List<User> users = userService.list();
        List<User> users1 = userService.list(wrapper);
        // 更新单条
        userService.update(user, wrapper);
        // save单条
        userService.save(user);
        // save多条
        userService.saveBatch(users);
        // 保存或更新
        userService.saveOrUpdate(user, wrapper);
        // 满足条件删除
        userService.remove(wrapper);
        userService.removeById(1);
    }
}
