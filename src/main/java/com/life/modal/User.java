package com.life.modal;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {

    @NotNull(message = "用户ID不能为空")
    private Long id;

    @NotNull(message = "用户账户不能为空")
    @Size(min = 6,max = 11,message = "账户长度只能为6～11位")
    private String name;

    @NotNull(message = "密码不能位空")
    @Size(min = 6,max = 11,message = "密码长度只能为6～11位")
    private String password;

    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
}
