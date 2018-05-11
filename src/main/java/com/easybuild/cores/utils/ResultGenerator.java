package com.easybuild.cores.utils;

/**
 * 
 * <p>
 *  响应结果生成工具
 * </p>
 *
 *
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    
    /**
     * 生成成功结果集
     */
    public static Result<String> genSuccessResult() {
        Result<String> result = new Result<String>();
        result.setResultCode(Constants.RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }
    
    /**
     * 生成成功结果集，并且有返回数据
     */
    public static <T> Result<T> genSuccessResult(T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(Constants.RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    /**
     * 生成失败结果集
     */
    public static Result<String> genFailResult(String message) {
        Result<String> result = new Result<String>();
        result.setResultCode(Constants.RESULT_CODE_SERVER_ERROR);
        if (message == null || message.length() < 1) {
            message = DEFAULT_FAIL_MESSAGE;
        }
        result.setMessage(message);
        return result;
    }

}