package com.taotao.service;

import taotaoUtil.EasyUiTreeResult;
import taotaoUtil.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<EasyUiTreeResult> getContentCategoryListTree(Long parentId);

    TaotaoResult deleteById(long id);

    TaotaoResult updateEntity(long id, String name);

    TaotaoResult addEntity(long parentId, String name);
}
