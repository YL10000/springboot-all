package com.life.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(200,"成功"),

    FAILED(500,"响应失败"),

    VALIDATE_FAILED(4001,"参数校验失败"),

    ;

    private int code;

    private String msg;




}
