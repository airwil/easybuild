package com.easybuild.cores.utils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 文件上传工具
 *
 */
public class FileUpload {
	/**
	 * @param localPath 本地路径
	 * @param file 上传文件
	 * @param request HttpServletRequest
	 */
	public static void uplaod(String localPath,MultipartFile file,HttpServletRequest request) {
		try {
		    byte[] bytes = file.getBytes();
		    String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, 
		    		file.getOriginalFilename().length());
			Path path=Paths.get(localPath,UUID.randomUUID().toString()+"."+type);
			Files.write(path, bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}