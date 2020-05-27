package com.life.advice;

import com.life.constant.ResultCode;
import com.life.vo.ResultVO;
import com.sun.javaws.exceptions.InvalidArgumentException;
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        ObjectError objectError=e.getBindingResult().getAllErrors().get(0);
        return  new ResultVO<>(ResultCode.FAILED,objectError.getDefaultMessage());
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResultVO<String> elValidatorArgumentExceptionHandler(InvalidArgumentException invalidArgumentException){
        return  new ResultVO<>(ResultCode.FAILED,invalidArgumentException.getMessage());
    }
}
