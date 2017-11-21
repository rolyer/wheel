package com.wheel.gateway.web.controller;

import com.wheel.gateway.api.UsersApi;
import com.wheel.gateway.api.entity.User;
import com.wheel.gateway.web.util.Convert;
import com.wheel.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rolyer on 17/11/16.
 */
@RestController()
@RequestMapping("/v2/frontapi")
public class UserController implements UsersApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<User> getUserById(@ApiParam(value = "需要获取用户的ID值", required = true) @PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(Convert.to(userService.queryById(Long.parseLong(userId.toString()))), HttpStatus.OK);
    }
}
