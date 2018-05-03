package com.taotao.service.impl;

import com.taotao.manager.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taotaoUtil.TaotaoResult;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public int add(TbItemParamItem tbItemParamItem) {
        int i = itemParamItemMapper.insert(tbItemParamItem);
        return i;
    }

    @Override
    public void addByParamDate(String itemParams, Long itemId) {
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParams);
        itemParamItemMapper.insertSelective(itemParamItem);
    }

    @Override
    public TaotaoResult findEntityByItemId(Long itemId) {
        TaotaoResult result = new TaotaoResult();
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> tbItemParamItems = itemParamItemMapper.selectByExampleWithBLOBs(example);
        result.setStatus(200);
        result.setData(tbItemParamItems.get(0));
        return result;
    }

    @Override
    public void update(Long itemId, String itemParams) {
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParams);
        itemParamItem.setUpdated(new Date());
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> tbItemParamItems = itemParamItemMapper.selectByExample(example);
        if(tbItemParamItems.size()>0){
            itemParamItemMapper.updateByExampleSelective(itemParamItem, example);
        }else{
            itemParamItemMapper.insertSelective(itemParamItem);
        }
    }
}
