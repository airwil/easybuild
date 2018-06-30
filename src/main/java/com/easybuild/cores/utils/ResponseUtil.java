package com.easybuild.cores.utils;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * Response工具类
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o){
        response.setContentType("text/html;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = null;
		try {
			out = response.getWriter();out.println(o.toString());
	        out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			 out.close();
		}
        
    }
}