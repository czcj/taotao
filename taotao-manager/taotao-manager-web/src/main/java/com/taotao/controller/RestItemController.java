package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemDescService;
import com.taotao.service.ItemParamItemService;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import taotaoUtil.TaotaoResult;

@Controller
@RequestMapping("/rest")
public class RestItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescService itemDescService;
    @Autowired
    private ItemParamItemService itemParamItemService;
    @RequestMapping("/page/item-edit")
    public String itemEdit(){
        return "item-edit";
    }
    @RequestMapping("/item/update")
    @ResponseBody
    public TaotaoResult itemUpdate(TbItem tbItem,String itemParams){
       return itemService.updateTbItem(tbItem,itemParams);
    }
    @RequestMapping("/item/delete")
    @ResponseBody
    public TaotaoResult itemDelete(String ids){
        TaotaoResult result = new TaotaoResult();
        try{
            itemService.deleteItemByIds(ids);
            result.setStatus(200);
        }catch (Exception  e){
            result.setStatus(500);
        }
        return  result;
    }
    @RequestMapping("/item/query/item/desc/{itemId}")
    @ResponseBody
    public TaotaoResult queryItemDesc(@PathVariable Long itemId){
        return itemDescService.findEntityByItemId(itemId);
    }
    @RequestMapping("/item/param/item/query/{itemId}")
    @ResponseBody
    public TaotaoResult queryItem(@PathVariable Long itemId){
        return itemParamItemService.findEntityByItemId(itemId);
    }
}
