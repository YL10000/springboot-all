package com.life.advice;

import com.life.constant.ResultCode;
import com.life.vo.ResultVO;
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
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        ObjectError objectError=e.getBindingResult().getAllErrors().get(0);
        return  new ResultVO<>(ResultCode.FAILED,objectError.getDefaultMessage());
    }
}
