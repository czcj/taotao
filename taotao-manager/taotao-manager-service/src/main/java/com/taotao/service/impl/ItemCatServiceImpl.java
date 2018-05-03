package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manager.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

import taotaoUtil.EasyUiTreeResult;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<EasyUiTreeResult> getItemCatList(long pid) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(pid);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		List<EasyUiTreeResult> results = new ArrayList<>();
		for(TbItemCat demo : list){
			EasyUiTreeResult easyUiTreeResult= new EasyUiTreeResult();
			easyUiTreeResult.setId(demo.getId());
			if(demo.getIsParent()){
				easyUiTreeResult.setState("closed");
			}else{
				easyUiTreeResult.setState("open");
			}
			easyUiTreeResult.setText(demo.getName());
			results.add(easyUiTreeResult);
		}
		
		return results;
	}

}
