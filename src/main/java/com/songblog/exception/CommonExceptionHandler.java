package com.songblog.exception;

import com.songblog.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {
    /**
     * 一般的参数绑定时候抛出的异常
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResult handleBindException(BindException ex){
        List<String> defaultmsg = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return CommonResult.failed(defaultmsg.get(0));
    }
    /**
     * @Description: 参数校验异常捕获 使用validation做参数校验，
     * 使用@notnull注解加上参数校验以及,@Vaild注解触发参数校验。
     * 此外需要用这个exceptionHandler捕获并按统一格式返回异常才能在前端展示
     * @Date: 2022/4/6
     * @Param e:
     **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handlerMethodArgumentNotVaildException(MethodArgumentNotValidException e){
        return CommonResult.failed(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
