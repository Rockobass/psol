package org.orz.psol.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.orz.psol.model.RespBean;
import org.orz.psol.model.User;
import org.orz.psol.mapper.UserMapper;
import org.orz.psol.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orz.psol.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-12-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return loadByUsername(s);
    }

    public User loadByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userMapper.selectOne(wrapper);
    }

    public boolean deleteByUUID(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        wrapper.eq("role","ROlE_admin");
        User user = userMapper.selectOne(wrapper);
        if (user != null) {
            user.setActivate(false);
            userMapper.updateById(user);
            return true;
        } else
            return false;
    }

    public boolean add(String id, String username, String password, String role) {
        password = new BCryptPasswordEncoder().encode(password);
        User user = new User(id,username,password,role);
        boolean success = false;
        try {
            userMapper.insert(user);
            success = true;
        }catch (Exception ignored) {

        }
        return success;
    }
}
