package com.wheel.gateway.web.util;

import com.wheel.dao.entity.User;

/**
 * Created by rolyer on 17/11/17.
 */
public class Convert {
    public static com.wheel.gateway.api.entity.User to(User user) {
        com.wheel.gateway.api.entity.User u = new com.wheel.gateway.api.entity.User();
        u.setId(user.getId());
        u.setName(user.getName());

        return u;
    }
}
