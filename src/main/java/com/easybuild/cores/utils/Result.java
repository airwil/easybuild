package com.easybuild.cores.utils;

/**
 * <p>
 * 通用结果类
 * </p>
 *
 * @author zhaowei
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class Result<T> {
	/**
	 * 状态码
	 */
	private Integer resultCode;
	
	/**
	 * 消息
	 */
	private String message;
	
	/**
	 * 数据
	 */
	private T data;

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
