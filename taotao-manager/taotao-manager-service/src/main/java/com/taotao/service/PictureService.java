package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import taotaoUtil.PictureResult;

public interface PictureService {

	PictureResult uploadFile(MultipartFile uploadFile);

}
