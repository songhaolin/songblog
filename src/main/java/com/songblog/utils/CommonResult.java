package com.songblog.utils;


import com.baomidou.mybatisplus.extension.api.IErrorCode;
import lombok.Data;

@Data
public class CommonResult<T>{
    private long code;
    private String message;
    private T data;

    protected CommonResult(){
    }

    protected CommonResult(long code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult(long code,String message){
        this.code =  code;
        this.message = message;
    }
    /**
     * @Description: 返回成功的结果
     * @Date: 2022/4/6
     * @Param data:获取的数据
     **/
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>(200,"操作成功",data);
    }
    /**
     * @Description: 返回成功的结果
     * @Date: 2022/4/6
     * @Param message: 返回的信息
     **/
    public static <T> CommonResult<T> success(String message){
        return new CommonResult<>(200,message);
    }
    /**
     * @Description: 返回成功的结果
     * @Date: 2022/4/6
     * @Param message: 成功的提示信息
     * @Param data: 成功获取的数据
     **/
    public static <T> CommonResult<T>success(String message,T data){
        return new CommonResult<T>(200,message,data);
    }
    /**
     * @Description: 返回失败的结果
     * @Date: 2022/4/6
     * @Param message: 失败的提示信息
     **/
    public static <T> CommonResult<T>failed(String message){
        return new CommonResult<T>(1000,message,null);
    }
    /**
     * @Description: 默认返回的失败提示信息
     * @Date: 2022/4/6

     **/
    public static <T> CommonResult<T>failed(){
        return failed("操作失败");
    }
    /**
     * @Description: 返回失败的错误码和错误提示信息
     * @Date: 2022/4/6
     * @Param code: 错误码
     * @Param message: 错误提示信息
     **/
    public static <T> CommonResult<T>failed(int code,String message){
        return new CommonResult<>(code,message);
    }
    /**
     * @Description: 根据baomi错误码返回失败信息
     * @Date: 2022/4/6
     * @Param errorCode:错误码
     **/
    public static <T>CommonResult<T>failed(IErrorCode errorCode){
        return new CommonResult<>(1000,"操作失败",null);
    }
    /**
     * @Description: 返回错误码，错误信息以及错误数据
     * @Date: 2022/4/6
     * @Param code:
     * @Param message:
     * @Param data:
     **/
    public static <T>CommonResult<T>failed(int code,String message,T data){
        return new CommonResult<>(code,message,data);
    }
    /**
     * @Description: 参数验证失败
     * @Date: 2022/4/6

     **/
    public static <T>CommonResult<T>validateFailed(){
        return failed("操作失败，参数验证异常");
    }
    /**
     * @Description: 参数验证失败的返回信息
     * @Date: 2022/4/6
     * @Param message:
     **/
    public static <T>CommonResult<T>validateFailed(String message){
        return new CommonResult<>(1000,message,null);
    }
}
