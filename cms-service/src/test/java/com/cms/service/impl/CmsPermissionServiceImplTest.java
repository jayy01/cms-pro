package com.cms.service.impl;

import com.cms.service.dto.CmsPermissionDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/29 15:43
 * @Version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class CmsPermissionServiceImplTest {

    @Test
    public void testPermission(){
        // 获取模拟数据
        List<CmsPermissionDto> cmsPermissionDtos = buildData();
        // 存放所有数据
        Map<Integer, CmsPermissionDto> permissionMap = Maps.newHashMap();
        // 存放parentId=0的数据
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        // 循环数据  进行处理
        cmsPermissionDtos.forEach(x -> {
            Integer id = x.getId();
            permissionMap.put(id,x);
            // 获取parentId
            Integer parentId = x.getParentId();
            // 判断是否为顶级菜单
            if (parentId == 0){
                permissionList.add(x);
            }else {
                CmsPermissionDto cmsPermissionDto = permissionMap.get(parentId);
                List<CmsPermissionDto> children = cmsPermissionDto.getChildren();
                if(CollectionUtils.isEmpty(children)){
                    children = Lists.newArrayList();
                }
                children.add(x);
                cmsPermissionDto.setChildren(children);
            }
        });

    }

    public List<CmsPermissionDto> buildData(){
        List<CmsPermissionDto> cmsPermissionList = Lists.newArrayList();

        CmsPermissionDto cmsPermissionDto1 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto2 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto3 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto4 = new CmsPermissionDto();

        cmsPermissionDto1.setId(1);
        cmsPermissionDto1.setParentId(0);
        cmsPermissionDto1.setName("测试1");
        cmsPermissionDto1.setPriority(1);

        cmsPermissionDto2.setId(2);
        cmsPermissionDto2.setParentId(1);
        cmsPermissionDto2.setName("测试2");
        cmsPermissionDto2.setPriority(2);

        cmsPermissionDto3.setId(3);
        cmsPermissionDto3.setParentId(2);
        cmsPermissionDto3.setName("测试3");
        cmsPermissionDto3.setPriority(4);

        cmsPermissionDto4.setId(4);
        cmsPermissionDto4.setParentId(3);
        cmsPermissionDto4.setName("测试4");
        cmsPermissionDto4.setPriority(4);

        cmsPermissionList.add(cmsPermissionDto1);
        cmsPermissionList.add(cmsPermissionDto2);
        cmsPermissionList.add(cmsPermissionDto3);
        cmsPermissionList.add(cmsPermissionDto4);

        return cmsPermissionList;
    }

}