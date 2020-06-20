package com.life.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.life.modal.UserEntity;
import com.life.service.UserService;
import com.sun.javaws.exceptions.InvalidArgumentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Api(value = "用户管理",tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户")
    @PostMapping("add")
    public Boolean addUser(@RequestBody @Validated UserEntity userEntity) throws InvalidArgumentException {
        //String message = SpringElValidator.messageThreadLocal.get();
        //Assert.isTrue(StrUtil.isEmpty(message),message);
        return userService.save(userEntity);
    }

    @ApiOperation("分页查询用户数据")
    @PostMapping("page")
    public Page<UserEntity> pageuser(int pageNum, int pageSize){
        Page<UserEntity> page = new Page<>(pageNum,pageSize);
        return userService.page(page);
    }

    @ApiOperation("导出用户数据")
    @GetMapping("export")
    public void exportUser(HttpServletResponse response) throws IOException {
        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("用户数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //默认失败也会进行返回部分数据，需要在catch中进行重置response，并输出错误日志
            EasyExcel.write(response.getOutputStream(), UserEntity.class)
                .autoCloseStream(Boolean.FALSE)//先不要自动关闭流
                .sheet("用户数据").doWrite(userService.list());
        }catch (Exception e){
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "导出失败" + e.getMessage());
            response.getWriter().println(JSONUtil.toJsonStr(map));
        }
    }

}
