package org.orz.psol.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.orz.psol.model.User;
import org.orz.psol.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orz.psol.service.interfaces.UserServiceInterface;
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
public class UserService extends ServiceImpl<UserMapper, User> implements UserServiceInterface, UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = loadByUsername(s);
        if (user == null)
            throw new UsernameNotFoundException(null);
        return user;
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
            user.setActivated(false);
            userMapper.updateById(user);
            return true;
        } else
            return false;
    }

    public boolean add(String id, String username, String password, String role) {
        password = new BCryptPasswordEncoder().encode(password);
        User user = new User(id,username,password,role,true);
        boolean success = false;
        try {
            userMapper.insert(user);
            success = true;
        }catch (Exception ignored) {

        }
        return success;
    }

}
