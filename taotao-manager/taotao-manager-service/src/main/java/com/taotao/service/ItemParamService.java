package com.taotao.service;

import com.taotao.pojo.TbItemParam;
import taotaoUtil.EasyUiDataGridResult;
import taotaoUtil.TaotaoResult;

public interface ItemParamService {

    TaotaoResult add(TbItemParam tbItemParam);

    EasyUiDataGridResult getItemParamList(Integer page, Integer rows);

    TaotaoResult queryCataLogById(long cid);
}
