package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsRolePromissionEntity;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/8 15:46
 * @Version 1.0
 */
public interface CmsRolePermissionMapper extends BaseMapper<CmsRolePromissionEntity,Integer> {
    /**
     * 批量插入
     * @param permissionList 权限集合
     * @param roleId 角色id
     */
    void batchInsert(@Param("permissionList")List<Integer> permissionList,@Param("roleId")Integer roleId);

    /**
     * 根据permissionId删除
     * @param permissionId
     */
    void deleteByPermissionId(Integer permissionId);

    /**
     * 根据roleId查询权限
     * @param roleId
     * @return
     */
    List<Integer> getPermissionByRoleId(Integer roleId);

    /**
     * 根据 roleId删除
     * @param roleId
     */
    void deleteByRoleId(Integer roleId);
}
