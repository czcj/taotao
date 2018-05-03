package com.taotao.service;

import com.taotao.pojo.TbItemParamItem;
import taotaoUtil.TaotaoResult;

public interface ItemParamItemService {

    public int add(TbItemParamItem tbItemParamItem);

    void addByParamDate(String itemParams,Long itemId);

    TaotaoResult findEntityByItemId(Long itemId);

    void update(Long id, String itemParams);
}
