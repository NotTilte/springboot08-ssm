package com.intouch.ssm.service;

import com.intouch.ssm.dao.UserMapper;
import com.intouch.ssm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User login(String email) {
        User user = userMapper.selectByEmailFaield(email);
        return user;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modfyInfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
