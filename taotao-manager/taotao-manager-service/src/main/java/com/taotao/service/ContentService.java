package com.taotao.service;

import com.taotao.pojo.TbContent;
import taotaoUtil.EasyUiDataGridResult;
import taotaoUtil.EasyUiTreeResult;
import taotaoUtil.TaotaoResult;

public interface ContentService {
    EasyUiDataGridResult queryContentList(Long categoryId, Integer page, Integer rows);

    TaotaoResult updateContent(TbContent content);

    TaotaoResult createContent(TbContent content);

    TaotaoResult deleteContent(String ids);
}
