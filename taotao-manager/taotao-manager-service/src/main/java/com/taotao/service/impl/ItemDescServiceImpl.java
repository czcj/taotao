package com.taotao.service.impl;

import com.taotao.manager.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taotaoUtil.TaotaoResult;

import java.util.Date;
import java.util.List;

@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private TbItemDescMapper itemDescMapper;


    @Override
    public TaotaoResult addItemDesc(Long id, String itemDesc) {
        TbItemDesc itemDescPojo = new TbItemDesc();
        itemDescPojo.setItemId(id);
        itemDescPojo.setItemDesc(itemDesc);
        itemDescPojo.setCreated(new Date());
        itemDescPojo.setUpdated(new Date());
        int i = itemDescMapper.insertSelective(itemDescPojo);
        TaotaoResult result = new TaotaoResult();
        if(i>0){
           result.setStatus(200);
        }
        return result;
    }

    @Override
    public TaotaoResult findEntityByItemId(Long itemId) {
        TaotaoResult result = new TaotaoResult();
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemDesc> tbItemDescs = itemDescMapper.selectByExampleWithBLOBs(example);
        result.setStatus(200);
        result.setData(tbItemDescs.get(0));
        return  result;
    }
}
