package com.wheel.service.impl;

import com.wheel.dao.UserDao;
import com.wheel.dao.entity.User;
import com.wheel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rolyer on 17/11/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User queryById(Long id) {
        return userDao.findOne(id.toString());
    }
}
