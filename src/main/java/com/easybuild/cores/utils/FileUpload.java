package com.easybuild.cores.utils;
import java.io.File;
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
	public static String uplaod(String localPath,MultipartFile file,HttpServletRequest request) {
		try {
			File filepath=new File(localPath);
			if(!filepath.exists()){
				filepath.mkdirs();
			}
			
		    byte[] bytes = file.getBytes();
		    String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, 
		    		file.getOriginalFilename().length());
		    String filename=UUID.randomUUID().toString()+"."+type;
			Path path=Paths.get(localPath,filename);
			Files.write(path, bytes);
			return filename;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}