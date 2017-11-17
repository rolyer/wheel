package com.wheel.gateway.web.controller;

import com.wheel.gateway.api.UsersApi;
import com.wheel.gateway.api.entity.User;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by rolyer on 17/11/16.
 */
@RestController()
@RequestMapping("/v2/frontapi")
public class UserController implements UsersApi {

    @Override
    public ResponseEntity<User> getUserById(@ApiParam(value = "需要获取用户的ID值", required = true) @PathVariable("userId") Integer userId) {
        User u = new User();
        u.setComplete(true);
        u.setName(UUID.randomUUID().toString().replaceAll("-", ""));
        u.setId(Long.parseLong(userId.toString()));
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
