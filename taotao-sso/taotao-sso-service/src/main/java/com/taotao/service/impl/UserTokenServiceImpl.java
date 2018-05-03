package com.taotao.service.impl;

import com.taotao.manager.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taotaoUtil.JedisUtil;
import taotaoUtil.Md5Util;

import java.util.List;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public String setUserToken(String phone, String password) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if(tbUsers != null && tbUsers.size() == 1 && tbUsers.get(0).getPassword().equals(Md5Util.getMD5(password))){
            String token  = tbUsers.get(0).getPassword();
            jedisUtil.set(token,phone,300);
            return token;
        }else{
            return null;
        }
    }
}
