package com.example.demo.config;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String s) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",s);

        User user=userService.getOne(queryWrapper);

        UserDetails userDetails= org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .passwordEncoder(passwordEncoder::encode)
                .roles(JSONObject.parseArray(user.getRoles(),String.class).toArray(new String[]{}))
                .build();

        return userDetails == null ? Mono.empty() : Mono.just(org.springframework.security.core.userdetails.User.withUserDetails(userDetails).build());
    }
}
