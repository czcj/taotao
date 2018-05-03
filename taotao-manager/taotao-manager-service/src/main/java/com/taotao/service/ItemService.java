package com.taotao.service;

import com.taotao.pojo.TbItem;
import taotaoUtil.EasyUiDataGridResult;
import taotaoUtil.TaotaoResult;

public interface ItemService {

	//查询商品列表
	public EasyUiDataGridResult  getItemList(Integer page,Integer rows);

	//增加商品
    TaotaoResult addItem(TbItem item,String itemDesc,String itemParams);

	TaotaoResult updateTbItem(TbItem tbItem,String itemParams);

	TaotaoResult deleteItemByIds(String ids);
}
