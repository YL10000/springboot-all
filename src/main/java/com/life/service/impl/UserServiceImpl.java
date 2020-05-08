package com.life.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.life.mapper.UserMapper;
import com.life.modal.UserEntity;
import com.life.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserEntity> implements UserService {

}
