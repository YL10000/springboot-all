package com.life.advice;

import com.life.constant.ResultCode;
import com.life.vo.ResultVO;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 处理参数验证失败的异常
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResultVO<String> methodArgumentNotValidExceptionHandler(Exception e){
        ObjectError objectError=null;
        if ( e instanceof  MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException= (MethodArgumentNotValidException) e;
            objectError=methodArgumentNotValidException.getBindingResult().getAllErrors().get(0);
        }else {
            BindException bindException= (BindException) e;
            objectError=bindException.getBindingResult().getAllErrors().get(0);
        }

        return  new ResultVO<>(ResultCode.FAILED,objectError.getDefaultMessage());
    }


    @ExceptionHandler(InvalidArgumentException.class)
    public ResultVO<String> elValidatorArgumentExceptionHandler(InvalidArgumentException invalidArgumentException){
        return  new ResultVO<>(ResultCode.FAILED,invalidArgumentException.getMessage());
    }
}
