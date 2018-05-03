package com.taotao.controller;

import java.util.Date;
import java.util.List;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.service.ItemCatService;
import com.taotao.service.ItemService;

import taotaoUtil.EasyUiDataGridResult;
import taotaoUtil.EasyUiTreeResult;
import taotaoUtil.TaotaoResult;

@Controller
@RequestMapping("item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemCatService itemCatService;
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUiDataGridResult itemList(Integer page,Integer rows){
		EasyUiDataGridResult resultList = itemService.getItemList(page, rows);
		return resultList;
	}
	@RequestMapping("/cat/list")
	@ResponseBody
	public List<EasyUiTreeResult> catList(@RequestParam(value="id",defaultValue="0")long pid){
		List<EasyUiTreeResult> resultList = null;
		resultList = itemCatService.getItemCatList(pid);
		return resultList;
	}
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult itemAdd(TbItem item,String desc,String itemParams){
		TaotaoResult result = itemService.addItem(item,desc,itemParams);
		return  result;
	}
	@RequestMapping("/param/list")
	@ResponseBody
	public EasyUiDataGridResult itemParamList(Integer page,Integer rows){
		EasyUiDataGridResult result = itemParamService.getItemParamList(page,rows);
		return  result;
	}
	@RequestMapping("/param/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult queryCatalogById(@PathVariable long cid){
		return itemParamService.queryCataLogById(cid);
	}
	@RequestMapping("/param/save/{cid}")
	@ResponseBody
	public TaotaoResult itemParamSave(String paramData,@PathVariable long cid){
		TbItemParam itemParam = new TbItemParam();
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		return itemParamService.add(itemParam);
	}
}
