package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taotaoUtil.EasyUiDataGridResult;
import taotaoUtil.TaotaoResult;

import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public TaotaoResult add(TbItemParam tbItemParam) {
        TaotaoResult result = new TaotaoResult();
        int i = tbItemParamMapper.insertSelective(tbItemParam);
        if(i> 0){
            result.setStatus(200);
        }else{
            result.setStatus(500);
        }
        return result;
    }

    @Override
    public EasyUiDataGridResult getItemParamList(Integer page, Integer rows) {
        EasyUiDataGridResult result = new EasyUiDataGridResult();
        PageHelper.startPage(page, rows);
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(tbItemParams);
        result.setRows(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TaotaoResult queryCataLogById(long cid) {
        TaotaoResult result = new TaotaoResult();
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);
        result.setStatus(200);
        if(tbItemParams.size()>0){
            result.setData(tbItemParams.get(0));
        }
        return result;
    }
}
