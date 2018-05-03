package com.taotao.rest.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.rest.service.ItemCatService;
import com.taotao.rest.util.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;


    @RequestMapping(value="itemcat/all",produces= MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        //把pojo转换成字符串
//        String json = JsonUtils.objectToJson(catResult);
        String json = JSON.toJSONString(catResult);
        //拼装返回值
        String result = callback + "(" + json + ");";
        return result;
    }
}
