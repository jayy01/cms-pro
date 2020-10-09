package com.cms.service.impl;

import com.cms.dao.mapper.TestMapper;
import com.cms.service.api.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/28 9:11
 * @Version 1.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    public Long getCount(){
        return testMapper.getCount();
    }

}
