package com.life.service.impl;

import com.life.modal.User;
import com.life.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Integer addUser(User user) {
        log.info("创建成功");
        return 1;
    }
}
