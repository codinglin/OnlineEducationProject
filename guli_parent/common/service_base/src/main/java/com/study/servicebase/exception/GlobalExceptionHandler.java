package com.study.servicebase.exception;

import com.study.commonutils.Exception.RRException;
import com.study.commonutils.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.study.commonutils.ResponseEntity;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //执行出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity error(Exception e){
        e.printStackTrace();
        return ResponseEntity.error().message("执行全局异常处理!");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResponseEntity error(ArithmeticException e){
        e.printStackTrace();
        return ResponseEntity.error().message("执行ArithmeticException异常处理!");
    }

    //自定义异常
    @ExceptionHandler(RRException.class)
    @ResponseBody
    public ResponseEntity error(RRException e){
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return ResponseEntity.error().code(e.getCode()).message(e.getMsg());
    }
}
