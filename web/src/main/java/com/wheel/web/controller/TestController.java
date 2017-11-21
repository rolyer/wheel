package com.wheel.web.controller;

import com.wheel.common.result.Result;
import com.wheel.domain.vo.TestReqVo;
import com.wheel.service.TestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Log4j2
public class TestController {

    @Autowired
    private TestService testService;


//    @RequestMapping(method = RequestMethod.GET, path = "city", produces = "application/json;;charset=UTF-8")
    @RequestMapping(method = RequestMethod.GET, path = "city")
    public Result getAll(@Validated TestReqVo testReqVo) {
        return new Result(testService.test());
    }


}
