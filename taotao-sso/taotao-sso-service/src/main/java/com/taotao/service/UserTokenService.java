package com.taotao.service;

import com.taotao.pojo.TbUserToken;

public interface UserTokenService {

    String setUserToken(String username, String password);
}
