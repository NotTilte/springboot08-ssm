package com.intouch.ssm.dao;

import com.intouch.ssm.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insert(User record);
    int insertSelective(User record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKey(User record);

    User selectByPrimaryKey(Integer id);

    //定义登录的dao方法
    User selectByEmailFaield(String email);
}