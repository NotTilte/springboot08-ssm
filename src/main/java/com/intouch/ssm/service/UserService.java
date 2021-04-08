package com.intouch.ssm.service;

import com.intouch.ssm.domain.User;

public interface UserService {

    //需求1：登录
    public User login(String email);

    //需求2：修改用户信息
    public void modfyInfo(User user);
}
