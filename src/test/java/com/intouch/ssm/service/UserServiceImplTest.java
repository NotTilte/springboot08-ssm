package com.intouch.ssm.service;

import com.intouch.ssm.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    void login() {
        String email="wangda@123.com";
        User user = service.login(email);
        if (user == null) {
            System.out.println("没有查询到用户");
            return;
        }
        System.out.println(user.getId()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getNickname()+" "+user.getLastLonginIp()
                +" "+user.getLastLoginTime()+" "+user.getLastLoginTime()+" "+user.getIsEmailVerify()+" "+user.getUserIntegral());
    }

    @Test
    void modfyInfo() {
        String email="wangda@123.com";
        User user=service.login(email);
        user.setUserIntegral(12000);

        service.modfyInfo(user);
    }
}