package com.taotao.rest.controller;

import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import taotaoUtil.TaotaoResult;

import java.util.List;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
    @RequestMapping("content/list/{categoryId}")
    @ResponseBody
    public TaotaoResult contentList(@PathVariable Long categoryId){
        List<TbContent> list = contentService.selectContentList(categoryId);
        return TaotaoResult.ok(list);
    }
}
