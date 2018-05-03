package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.taotao.service.PictureService;
import taotaoUtil.PictureResult;

@Controller
public class UploadController {
	@Autowired
	PictureService pictureService;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public String imgUpload(MultipartFile uploadFile){
		//调用service上传图片
		PictureResult pictureResult = pictureService.uploadFile(uploadFile);
		//返回上传结果
		String s = JSON.toJSONString(pictureResult);
		return s;
	}
}
