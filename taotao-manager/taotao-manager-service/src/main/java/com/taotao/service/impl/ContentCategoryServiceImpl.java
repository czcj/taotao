package com.taotao.service.impl;

import com.taotao.manager.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taotaoUtil.EasyUiTreeResult;
import taotaoUtil.TaotaoResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;
    @Override
    public List<EasyUiTreeResult> getContentCategoryListTree(Long parentId) {
        List<EasyUiTreeResult> result = new ArrayList<>();
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = contentCategoryMapper.selectByExample(example);
        for(TbContentCategory demo : tbContentCategories){
            EasyUiTreeResult resultDemo = new EasyUiTreeResult();
            resultDemo.setId(demo.getId());
            if(demo.getIsParent()){
                resultDemo.setState("closed");
            }else{
                resultDemo.setState("open");
            }
            resultDemo.setText(demo.getName());
            result.add(resultDemo);
        }
        return result;
    }

    @Override
    public TaotaoResult deleteById(long id) {

        TaotaoResult result = null;
        try {
            result = new TaotaoResult();
            //根据id找到此广告实例对象
            TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
            int i = deleteCategory(id);
            if(i>0){

                //删除当前实例成功。判断父节点目录是否成为了叶子目录
                TbContentCategoryExample example = new TbContentCategoryExample();
                TbContentCategoryExample.Criteria criteria = example.createCriteria();
                criteria.andParentIdEqualTo(contentCategory.getParentId());
                List<TbContentCategory> tbContentCategories = contentCategoryMapper.selectByExample(example);
                if(tbContentCategories.size()<1){
                    //更改父节点为叶子目录
                    TbContentCategory contentCategory1 = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
                    contentCategory1.setIsParent(false);
                    contentCategoryMapper.updateByPrimaryKeySelective(contentCategory1);
                }
            }
            result.setStatus(200);
            return result;
        } catch (Exception e) {
            result.setStatus(500);
            return result;
        }
    }

    private int deleteCategory(Long id){
        //根据id找到此广告实例对象
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        //判断此实例是否是叶子类目
        int i = 0;
        if(contentCategory.getIsParent()){
            //不是叶子类目
            //找到子类目集合
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(contentCategory.getId());
            List<TbContentCategory> tbContentCategories = contentCategoryMapper.selectByExample(example);

            for(TbContentCategory demo : tbContentCategories){
                deleteCategory(demo.getId());
            }
            //删除当前实体
            i = contentCategoryMapper.deleteByPrimaryKey(id);
        }else{
            //删除当前实体
            i = contentCategoryMapper.deleteByPrimaryKey(id);
        }
        return i;
    }
    @Override
    public TaotaoResult updateEntity(long id, String name) {
        TaotaoResult result = new TaotaoResult();
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setId(id);
        contentCategory.setName(name);
        int i = contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        if(i>0){
            result.setStatus(200);
        }else{
            result.setStatus(500);
        }
        return result;
    }

    @Override
    public TaotaoResult addEntity(long parentId, String name) {
        TaotaoResult result = new TaotaoResult();
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategory.setParentId(parentId);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);
        int i = contentCategoryMapper.insertSelective(contentCategory);
        int i1 = 0;
        if(i>0){
            TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(parentId);
            category.setIsParent(true);
            i1 = contentCategoryMapper.updateByPrimaryKeySelective(category);
        }
        if(i1 >0){
            result.setStatus(200);
        }else{
            result.setStatus(500);
        }
        return result;
    }

}
