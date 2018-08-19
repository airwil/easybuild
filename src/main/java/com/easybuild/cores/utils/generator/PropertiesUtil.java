package com.easybuild.cores.utils.generator;

import org.springframework.util.StringUtils;

import com.easybuild.cores.utils.DateUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * <p>
 * Properties读写工具类
 * </p>
 *
 *
 */
public class PropertiesUtil {
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public PropertiesUtil(String path) {
		super();
		this.path = path;
	}
	
	
	/**
	 * 添加/更新
	 */
	public void write(String key, String value, String doc) {
		String realpath = System.getProperty("user.dir") + "/" + path;
		File sourceFile=new File(realpath);
		//String sourceFileName=sourceFile.getName();//原文件名称
		File newFile=new File(realpath+".temp");
		PrintWriter writer=null;
		FileOutputStream out=null;
		FileInputStream in=null;
		BufferedReader reader=null;
		
		try {
			//1.读取
			in=new FileInputStream(sourceFile);
			out=new FileOutputStream(newFile);
			reader=new BufferedReader(new InputStreamReader(in, "utf-8"));
			writer=new PrintWriter(new OutputStreamWriter(out,"utf-8"));
			boolean isExistKey=false;
			//2.添加或更新，并保存到临时文件
			String line=null;
			while ((line=reader.readLine())!=null){
				if(line.startsWith(key+"=")) {
					isExistKey=true;
					if(!StringUtils.isEmpty(doc)){
						writer.println("#"+doc);
					}
					line=key+"="+value;
				}
				writer.println(line);
			}
			//如果不存在相同的key,则追加数据
			if(!isExistKey){
				writer.print(key+"="+value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		//3.将原文件备份
		String bakName=System.getProperty("user.dir")
				+"/src/main/resources/bak/"
				+DateUtil.getCurrentTimestamp()
				+".bak";
		sourceFile.renameTo(new File(bakName));

		//4.临时文件取代原文件
		newFile.renameTo(new File(realpath));
	}
	
	
	
	

	
	/**
	 * 读取所有
	 */
	public Map<String, String> readAll() {
		Map<String, String> map = new HashMap<String, String>();
		Properties prop = new OrderedProperties();
		InputStream in = null;
		try {
			// 获取配置绝对文件路径
			String realpath = System.getProperty("user.dir") + "/" + path;
			in=new FileInputStream(realpath);
			// 加载配置文件
			prop.load(new InputStreamReader(in, "utf-8"));
			Set<Object> keys = prop.keySet();// 返回属性key的集合
			for (Object key : keys) {
				System.out.println(key.toString() + "=" + prop.get(key));
				map.put(key.toString(), prop.get(key).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	/**
	 * 根据key读取value
	 */
	public String getValue(String key) {
		String value=null;
		Properties prop = new OrderedProperties();
		InputStream in = null;
		try {
			// 获取配置绝对文件路径
			String realpath = System.getProperty("user.dir") + "/" + path;
			in=new FileInputStream(realpath);
			// 加载配置文件
			prop.load(new InputStreamReader(in, "utf-8"));
			//根据key获取value
			value=prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
	
	/**
	 * 判断key是否存在
	 */
	public boolean exist(String key) {
		boolean result=false;
		Properties prop = new Properties();
		InputStream in = null;
		try {
			// 获取配置绝对文件路径
			String realpath = System.getProperty("user.dir") + "/" + path;
			in=new FileInputStream(realpath);
			// 加载配置文件
			prop.load(new InputStreamReader(in, "utf-8"));
			result=prop.containsKey(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
