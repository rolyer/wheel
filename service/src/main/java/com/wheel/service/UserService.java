package com.wheel.service;

import com.wheel.dao.entity.User;

/**
 * Created by rolyer on 17/11/17.
 */
public interface UserService {
    User queryById(Long id);
}
