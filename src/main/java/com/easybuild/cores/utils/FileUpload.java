package com.easybuild.cores.utils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
			Path path=Paths.get(localPath,file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}