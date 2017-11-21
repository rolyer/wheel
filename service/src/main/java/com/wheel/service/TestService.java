package com.wheel.service;


import com.wheel.dao.mapper.TestMapper;
import com.wheel.dao.po.CityPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<CityPo> test() {

        List<CityPo> list = testMapper.selectAll();
        return list;
    }


}
