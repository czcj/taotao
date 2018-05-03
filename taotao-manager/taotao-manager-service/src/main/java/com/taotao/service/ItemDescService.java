package com.taotao.service;

import taotaoUtil.TaotaoResult;

public interface ItemDescService {
    TaotaoResult addItemDesc(Long id, String itemDesc);

    TaotaoResult findEntityByItemId(Long itemId);
}
