package com.life.vo;

import com.life.constant.ResultCode;
import lombok.Getter;

@Getter
public class ResultVO<T> {

    //状态码
    private int code;

    //返回的数据
    private T data;

    //响应说明
    private String msg;


    public ResultVO(T data) {
        this(ResultCode.SUCCESS,data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code=resultCode.getCode();
        this.msg=resultCode.getMsg();
        this.data = data;
    }
}
