package com.easybuild.cores.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.easybuild.cores.utils.FileUpload;


@RestController
@RequestMapping("/upload")
public class UploadController {
	    // 读取配置文件
		@Value("${uploadPath}")
		private String uploadPath;

		/**
		 * 上传图片
		 */
		@RequestMapping(value="/image",method=RequestMethod.POST)
		public Map<String, Object> uploadPicture(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
			Map<String, Object> map=new HashMap<>();
			String filename = FileUpload.uplaod(uploadPath, file,request);
			if(filename!=null){
				map.put("data", "/pic/"+filename);
				map.put("code", 200);
			}
			return map;
		}
}
