package com.taotao.controller;

import com.taotao.pojo.TbContent;
import com.taotao.service.ContentCategoryService;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import taotaoUtil.EasyUiDataGridResult;
import taotaoUtil.EasyUiTreeResult;
import taotaoUtil.TaotaoResult;

import java.util.List;

@Controller
public class ContentController {
    @Autowired
    ContentService contentService;
    @Autowired
    ContentCategoryService contentCategoryService;
    @RequestMapping("content/category/list")
    @ResponseBody
    public List<EasyUiTreeResult> contentCategoryList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        return contentCategoryService.getContentCategoryListTree(parentId);
    }
    @RequestMapping("content/category/delete")
    @ResponseBody
    public TaotaoResult contentCategoryDelete(long id){
        return contentCategoryService.deleteById(id);
    }
    @RequestMapping("content/category/update")
    @ResponseBody
    public TaotaoResult contentCategoryUpdate(long id,String name){
        return contentCategoryService.updateEntity(id,name);
    }
    @RequestMapping("content/category/create")
    @ResponseBody
    public TaotaoResult contentCategoryCreate(long parentId,String name){
        return contentCategoryService.addEntity(parentId,name);
    }
    @RequestMapping("content/query/list")
    @ResponseBody
    public EasyUiDataGridResult contentQueryList(Long categoryId,Integer page,Integer rows){
        return contentService.queryContentList(categoryId,page,rows);
    }
    @RequestMapping("rest/content/edit")
    @ResponseBody
    public TaotaoResult contentEdit(TbContent content){
        return contentService.updateContent(content);
    }
    @RequestMapping("content/save")
    @ResponseBody
    public TaotaoResult contentCreate(TbContent content){
        return contentService.createContent(content);
    }

    @RequestMapping("content/delete")
    @ResponseBody
    public TaotaoResult contentDelete(String ids){
        return contentService.deleteContent(ids);
    }
}
