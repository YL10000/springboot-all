package com.life.modal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@TableName("t_user")
public class UserEntity extends Model<UserEntity> {

  @NotNull(message = "用户ID不能为空")
  @TableId(type = IdType.AUTO,value = "user_id")
  private Long id;

  @NotNull(message = "用户账户不能为空")
  @Size(min = 6,max = 11,message = "账户长度只能为6～11位")
  @TableField("user_name")
  private String name;

  @NotNull(message = "密码不能位空")
  @Size(min = 6,max = 11,message = "密码长度只能为6～11位")
  private String password;

  @NotNull(message = "邮箱不能为空")
  @Email(message = "邮箱格式不正确")
  private String email;
}
