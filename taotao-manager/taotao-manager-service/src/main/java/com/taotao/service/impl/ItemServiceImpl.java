package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import com.taotao.service.ItemDescService;
import com.taotao.service.ItemParamItemService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

import org.springframework.transaction.annotation.Transactional;
import taotaoUtil.EasyUiDataGridResult;
import taotaoUtil.IDUtils;
import taotaoUtil.TaotaoResult;

@Service
public class ItemServiceImpl implements ItemService {

	private static Log logger1 = LogFactory.getLog("myTest1");
	private static Log logger2 = LogFactory.getLog("myTest2");
	@Autowired
	TbItemMapper itemMapper;
	@Autowired
	ItemDescService itemDescService;
	@Autowired
	ItemParamItemService itemParamItemService;

	@Override
	public EasyUiDataGridResult getItemList(Integer page, Integer rows) {
		EasyUiDataGridResult result = new EasyUiDataGridResult();
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> tbItemList = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);
		result.setTotal(pageInfo.getTotal());
		result.setRows(tbItemList);
		return result;
	}

	@Override
	public TaotaoResult addItem(TbItem item,String itemDesc,String itemParams) {
		long l = IDUtils.genItemId();
		item.setId(l);
		item.setStatus(Byte.valueOf("1"));
		item.setCreated(new Date(System.currentTimeMillis()));
		item.setUpdated(new Date(System.currentTimeMillis()));
		int insert =itemMapper.insertSelective(item);
		TaotaoResult result = new TaotaoResult();
		if(insert > 0){
			itemParamItemService.addByParamDate(itemParams,item.getId());
			result= itemDescService.addItemDesc(item.getId(), itemDesc);
		}else{
			result.setStatus(500);
		}
		return  result;
	}

	@Override
	public TaotaoResult updateTbItem(TbItem tbItem,String itemParams) {
		TaotaoResult result = new TaotaoResult();
		int i = itemMapper.updateByPrimaryKeySelective(tbItem);
		itemParamItemService.update(tbItem.getId(),itemParams);
		if(i > 0){
			result.setStatus(200);
		}else{
			result.setStatus(500);
		}
		return  result;
	}

	@Override
	@Transactional
	public TaotaoResult deleteItemByIds(String ids) {
		TaotaoResult result = new TaotaoResult();
			String[] idArray = ids.split(",");
			for(String id : idArray){
				itemMapper.deleteByPrimaryKey(Long.valueOf(id));
			}
		return  result;
	}

}
