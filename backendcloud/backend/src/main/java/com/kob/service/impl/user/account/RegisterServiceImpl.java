package com.kob.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kob.mapper.UserMapper;
import com.kob.pojo.User;
import com.kob.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmPassword) {
        Map<String, String> map = new HashMap<>();
        if(username.isEmpty()){
            map.put("error_message", "用户名为空");
            return map;
        }
        if(password.isEmpty()){
            map.put("error_message", "密码为空");
            return map;
        }
        username = username.trim();
        if(username.length() == 0){
            map.put("error_message", "用户名为空");
            return map;
        }
        if(password.length() == 0 || confirmPassword.length() == 0){
            map.put("error_message", "密码为空");
            return map;
        }
        if(username.length() > 20){
            map.put("error_message", "用户名过长");
            return map;
        }
        if(password.length() > 20){
            map.put("error_message", "密码过长");
            return map;
        }
        if(!password.equals(confirmPassword)){
            map.put("error_message", "确认密码不一致");
            return map;
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        final List<User> users = userMapper.selectList(lambdaQueryWrapper);
        if(!users.isEmpty()){
            map.put("error_message", "用户名已存在");
            return map;
        }
        String photo = "https://cdn.acwing.com/media/user/profile/photo/50802_sm_3ae6e2daf7.jpg";
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, username, encodedPassword, photo, 1500);

        userMapper.insert(user);
        map.put("error_message", "success");
        return map;
    }
}
