package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import taotaoUtil.HttpClientUtil;
import taotaoUtil.TaotaoResult;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {
    //http://127.0.0.1:1029/rest
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    //    /content/list/89
    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;


    @Override
    public String getContentList() {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String result = "";
        try {
            result = httpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //把字符串转换成TaotaoResult
        try {
            TaotaoResult taotaoResult = JSON.parseObject(result,TaotaoResult.class);
            //取内容列表
//            List<TbContent> list = (List<TbContent>) taotaoResult.getData();
//            String listStr = JSON.toJSONString(list);
//            list = JSON.parseArray(listStr, TbContent.class);
//            List<Map> resultList = new ArrayList<>();
//            //创建一个jsp页码要求的pojo列表
//            for (TbContent tbContent : list) {
//                Map map = new HashMap<>();
//                map.put("src", tbContent.getPic());
//                map.put("height", 240);
//                map.put("width", 670);
//                map.put("srcB", tbContent.getPic2());
//                map.put("widthB", 550);
//                map.put("heightB", 240);
//                map.put("href", tbContent.getUrl());
//                map.put("alt", tbContent.getSubTitle());
//                resultList.add(map);
//            }
            return JSON.toJSONString(taotaoResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
