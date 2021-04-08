package com.intouch.ssm.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    @NotBlank(message = "用户邮箱不能为空必须填写!")
    @Email(message = "用户邮箱不符合格式重新填写!")
    private String email;

    private String nickname;


    @NotBlank(message = "用户密码不能为空必须填写！")
    @Length(min=6,max = 8,message = "密码长度必须在六到八个字符之间!")
    private String password;

    private Integer userIntegral;

    private String isEmailVerify;

    private String emailVerifyCode;

    private Long lastLoginTime;

    private String lastLonginIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }

    public String getIsEmailVerify() {
        return isEmailVerify;
    }

    public void setIsEmailVerify(String isEmailVerify) {
        this.isEmailVerify = isEmailVerify == null ? null : isEmailVerify.trim();
    }

    public String getEmailVerifyCode() {
        return emailVerifyCode;
    }

    public void setEmailVerifyCode(String emailVerifyCode) {
        this.emailVerifyCode = emailVerifyCode == null ? null : emailVerifyCode.trim();
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLonginIp() {
        return lastLonginIp;
    }

    public void setLastLonginIp(String lastLonginIp) {
        this.lastLonginIp = lastLonginIp == null ? null : lastLonginIp.trim();
    }
}