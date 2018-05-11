package com.easybuild.cores.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.springframework.util.StringUtils;


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
		PrintWriter writer=null;
		FileOutputStream out=null;
		FileInputStream in=null;
		BufferedReader reader=null;
		
		try {
			// 获取配置绝对文件路径
			String realpath = System.getProperty("user.dir") + "/" + path;
			out=new FileOutputStream(realpath);
			in=new FileInputStream(realpath);
			
			reader=new BufferedReader(new InputStreamReader(in, "utf-8"));
			
			//根据是否存在相同的key判断更新或者添加操作
			if(this.exist(key)) {//更新
				System.out.println("=====更新=====");
				writer=new PrintWriter(out,false);
				writer.println("");
				String line=null;
				while((line=reader.readLine())!=null){
					if(line.startsWith(key+"=")) {
						line=key+"="+value;
					}
					writer.println(line);
				}
			}else {//添加
				System.out.println("=====添加=====");
				writer=new PrintWriter(out,true);
				if(!StringUtils.isEmpty(doc)) {
					writer.println("#"+doc);
				}
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
