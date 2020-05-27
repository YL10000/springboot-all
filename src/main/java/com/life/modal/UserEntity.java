package com.life.modal;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.life.constant.SpringEl;
import com.life.constant.InList;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@TableName("t_user")
@SpringEl(conditions = {"#UserEntity.id>10,id必须大于10"})
public class UserEntity extends Model<UserEntity> {

  @NotNull(message = "用户ID不能为空")
  @TableId(type = IdType.AUTO,value = "user_id")
  @ExcelProperty(index = 0,value = "用户ID")
  private Long id;

  @NotNull(message = "用户账户不能为空")
  @Size(min = 6,max = 11,message = "账户长度只能为6～11位")
  @TableField("user_name")
  @ExcelProperty(index = 1,value = "用户账户")
  private String name;

  @NotNull(message = "密码不能位空")
  @Size(min = 6,max = 11,message = "密码长度只能为6～11位")
  @ExcelIgnore
  private String password;

  @NotNull(message = "邮箱不能为空")
  @Email(message = "邮箱格式不正确")
  @ExcelProperty("邮箱")
  private String email;

  @NotNull(message = "生日不能为空")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @ExcelProperty(value = "生日")
  @com.alibaba.excel.annotation.format.DateTimeFormat("yyyy-MM-dd")
  private Date birthday;

  @NotNull
  @InList(list = {"1","2","3"})
  @TableField(exist = false)
  private int isDeleted;
}
