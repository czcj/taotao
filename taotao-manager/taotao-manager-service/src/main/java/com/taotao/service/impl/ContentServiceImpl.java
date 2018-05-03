package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taotaoUtil.EasyUiDataGridResult;
import taotaoUtil.TaotaoResult;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EasyUiDataGridResult queryContentList(Long categoryId, Integer page, Integer rows) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(page,rows);
        List<TbContent> tbContents = contentMapper.selectByExample(example);
        PageInfo<TbContent> contentInfos = new PageInfo<TbContent>(tbContents);
        EasyUiDataGridResult result = new EasyUiDataGridResult();
        result.setTotal(contentInfos.getTotal());
        result.setRows(contentInfos.getList());
        return result;
    }

    @Override
    @Transactional
    public TaotaoResult updateContent(TbContent content) {
        TaotaoResult result = new TaotaoResult();
        int i = contentMapper.updateByPrimaryKeyWithBLOBs(content);
        if(i>0){
            result.setStatus(200);
        }else{
            result.setStatus(500);
        }
        return result;
    }

    @Override
    public TaotaoResult createContent(TbContent content) {
        TaotaoResult result = new TaotaoResult();
        int i = contentMapper.insertSelective(content);
        if(i>0){
            result.setStatus(200);
        }else{
            result.setStatus(500);
        }
        return result;
    }

    @Override
    public TaotaoResult deleteContent(String ids) {
        String[] idsStr = ids.split(",");
        int i =1;
        TaotaoResult result = new TaotaoResult();
        for(String id : idsStr){
            int k = contentMapper.deleteByPrimaryKey(Long.valueOf(id));
            if(i<0){
                result.setStatus(500);
                return result;
            }
        }
        result.setStatus(200);
        return result;
    }
}
