package com.taotao.rest.service.impl;

import com.taotao.manager.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.service.ItemCatService;
import com.taotao.rest.util.CatNode;
import com.taotao.rest.util.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    TbItemCatMapper itemCatMapper;
    @Override
    public CatResult getItemCatList() {
        TbItemCatExample example = new TbItemCatExample();
        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
        CatResult catResult = new CatResult();
        catResult.setData(getCatList(0));
        return catResult;
    }

    private List<?> getCatList(long parentId) {
        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //返回值list
        List resultList = new ArrayList<>();
        //向list中添加节点
        for (TbItemCat tbItemCat : list) {
            //判断是否为父节点
            if (tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setN("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                } else {
                    catNode.setN(tbItemCat.getName());
                }
                catNode.setU("/products/"+tbItemCat.getId()+".html");
                catNode.setI(getCatList(tbItemCat.getId()));

                resultList.add(catNode);
                //如果是叶子节点
            } else {
                resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
            }
        }
        return resultList;
    }
}
