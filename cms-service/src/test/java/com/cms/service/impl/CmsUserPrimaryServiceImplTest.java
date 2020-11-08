package com.cms.service.impl;


import com.cms.dao.entity.CmsUserPrimaryEntity;
import com.cms.dao.mapper.CmsUserPrimaryMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/19 15:34
 * @Version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class CmsUserPrimaryServiceImplTest {

    @Mock
    private CmsUserPrimaryMapper cmsUserPrimaryMapper;

    @Test
    public void test(){
        when(cmsUserPrimaryMapper.selectById(1)).thenReturn(new CmsUserPrimaryEntity());
        CmsUserPrimaryEntity cmsUserPrimaryEntity = cmsUserPrimaryMapper.selectById(1);
        log.info("entity=[{}]",cmsUserPrimaryEntity);
    }

}