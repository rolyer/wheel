package com.wheel.dao;

import com.wheel.dao.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by rolyer on 17/11/17.
 */
@Component("userDao")
public interface UserDao extends CrudRepository<User, String> {
}
