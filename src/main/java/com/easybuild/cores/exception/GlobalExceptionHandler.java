package com.easybuild.cores.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log=LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception ex) {
    	log.error("【系统异常】{}",ex);
        return "/error";
    }
}
