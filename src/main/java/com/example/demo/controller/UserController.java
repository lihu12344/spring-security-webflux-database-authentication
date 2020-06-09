package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lihu
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserService userService;

    @RequestMapping("/add")
    public String addUser(){
        User user=new User();

        user.setName("瓜田李下");
        user.setAge(20);
        user.setPassword("123456");

        List<String> roles=new ArrayList<>();
        roles.add("user");
        roles.add("admin");
        String rolesString=JSONObject.toJSONString(roles);
        user.setRoles(rolesString);

        userService.save(user);

        return "success";
    }
}

