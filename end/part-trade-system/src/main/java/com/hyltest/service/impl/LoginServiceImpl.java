package com.hyltest.service.impl;

import com.hyltest.mapper.AdminMapper;
import com.hyltest.mapper.CustomerMapper;
import com.hyltest.mapper.MerchantMapper;
import com.hyltest.pojo.VO.UserVO;
import com.hyltest.pojo.entity.Admin;
import com.hyltest.pojo.entity.Customer;
import com.hyltest.pojo.entity.Merchant;
import com.hyltest.service.LoginService;
import com.hyltest.utils.JWTUtils;
import com.hyltest.utils.MD5Utils;
import com.hyltest.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public void register(UserVO userVO) {
        userVO.setCreateTime(TimeUtils.getCurrentTime());
        userVO.setUpdateTime(TimeUtils.getCurrentTime());
        //将密码进行md5加密后保存
        userVO.setPassword(MD5Utils.encrypt(userVO.getPassword()));
        //根据选择的角色对对应表进行增加
        if (userVO.getRole() == 1){
            merchantMapper.register(userVO);
        }else if (userVO.getRole() == 2){
            customerMapper.register(userVO);
        }
    }

    @Override
    public String login(UserVO userVO) {
        //根据选择的登录角色查询对应表是否存在该用户
        int role=userVO.getRole();
        if (role == 1){
            //供应商登录
            Merchant user = merchantMapper.getInfoByName(userVO);
            //如果存在该用户，判定密码是否正确（转为md5进行比较）
            if (user != null && MD5Utils.encrypt(userVO.getPassword()).equals(user.getPassword())){
                //如果密码正确，则返回jwt令牌（存储id，name，role）
                Map<String, Object> claims = genrateInfoMap(user.getId(), user.getName(),userVO.getRole());
                return JWTUtils.generateToken(claims);
            }else {
                return "账户非法或密码错误";
            }
        }else if (role == 2){
            //顾客登录
            Customer user = customerMapper.getInfoByName(userVO);
            if (user != null && MD5Utils.encrypt(userVO.getPassword()).equals(user.getPassword())){
                Map<String, Object> claims = genrateInfoMap(user.getId(), user.getName(),userVO.getRole());
                return JWTUtils.generateToken(claims);
            }else {
                return "账户非法或密码错误";
            }
        }else {
            Admin user = adminMapper.getInfoByName(userVO);
            if (user != null && MD5Utils.encrypt(userVO.getPassword()).equals(user.getPassword())){
                Map<String, Object> claims = genrateInfoMap(user.getId(), user.getName(),userVO.getRole());
                return JWTUtils.generateToken(claims);
            }else {
                return "账户非法或密码错误";
            }
        }
    }


    /**
     * 生成令牌数据
     * @param id 用户id
     * @param name 用户名
     * @param role 角色
     * @return 令牌数据
     */
    private Map<String, Object> genrateInfoMap(Integer id, String name, int role) {
        return Map.of("id", id, "name", name, "role", role);
    }


}
