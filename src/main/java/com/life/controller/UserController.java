package com.life.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.life.modal.UserEntity;
import com.life.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "用户管理",tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户")
    @PostMapping("add")
    public Boolean addUser(@RequestBody @Valid UserEntity userEntity){
        return userService.save(userEntity);
    }

    @ApiOperation("分页查询用户数据")
    @PostMapping("page")
    public Page<UserEntity> pageuser(int pageNum, int pageSize){
        Page<UserEntity> page = new Page<>(pageNum,pageSize);
        return userService.page(page);
    }

}
