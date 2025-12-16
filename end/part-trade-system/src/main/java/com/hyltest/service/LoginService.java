package com.hyltest.service;

import com.hyltest.pojo.VO.UserVO;

public interface LoginService {
    /**
     *  注册
     * @param userVO 注册信息
     */
    void register(UserVO userVO);

    /**
     * 登录
     *
     * @param userVO 登录用户信息
     */
    String login(UserVO userVO);
}
