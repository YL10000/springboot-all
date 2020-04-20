package com.life.exception;

import com.life.constant.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class APIException extends RuntimeException {

    private int code = ResultCode.FAILED.getCode();

    private String msg="接口错误";


    public APIException(String msg){
        this(ResultCode.FAILED.getCode(),msg);
    }

}
