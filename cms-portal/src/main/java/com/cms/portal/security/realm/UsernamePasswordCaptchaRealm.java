package com.cms.portal.security.realm;

import com.cms.dao.enums.UserStatusEnum;
import com.cms.service.api.CmsRoleService;
import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserDto;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/28 14:41
 * @Version 1.0
 */
public class UsernamePasswordCaptchaRealm extends AuthorizingRealm {

    @Autowired
    CmsUserService cmsUserService;
    @Autowired
    CmsRoleService cmsRoleService;
    /**
     * 鉴权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        CmsUserDto cmsUserDto = (CmsUserDto) principalCollection.getPrimaryPrincipal();
        List<String> actions = cmsRoleService.getRoleActionsByUserId(cmsUserDto.getId());
        simpleAuthorizationInfo.addStringPermissions(actions);
        return simpleAuthorizationInfo;
    }

    /**
     * 重写isPermitted 如果是超管 不用去鉴权  不是返回false  重新鉴权
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission){
        CmsUserDto cmsUserDto = (CmsUserDto) principals.getPrimaryPrincipal();
        if(BooleanUtils.isTrue(cmsUserDto.getSupper())){
            return true;
        }
        return super.isPermitted(principals,permission);
    }

    /**
     * 鉴权  实现用户名  密码的比对
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 获取密码
        //Object credentials = authenticationToken.getCredentials();
        // 查找用户是否存在
        CmsUserDto cmsUserDto = cmsUserService.selectByUsername(username);
        if(Objects.isNull(cmsUserDto)){
            throw new UnknownAccountException();
        }
        // 校验用户状态 是否禁用
        //verifyStatus(cmsUserDto.getStatus());
        if(!cmsUserDto.getStatus()){
            throw new DisabledAccountException("该账号已被禁用，请联系管理员");
        }
        //比对密码
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(cmsUserDto, cmsUserDto.getPassword(),
                ByteSource.Util.bytes(cmsUserDto.getSalt()), getName());
        // 清除认证信息
        super.clearCachedAuthenticationInfo(simpleAuthenticationInfo.getPrincipals());
        return simpleAuthenticationInfo;
    }

    /**
     * 验证用户状态 弃用
     */
    private void verifyStatus(UserStatusEnum userStatusEnum){
        if(UserStatusEnum.DISABLED.equals(userStatusEnum)){
            throw new DisabledAccountException("该账号已被禁用，请联系管理员");
        }
        if(UserStatusEnum.LOCKED.equals(userStatusEnum)){
            throw new DisabledAccountException("该账号已被锁定，请联系管理员");
        }
        if(UserStatusEnum.UNACTIVATED.equals(userStatusEnum)){
            throw new DisabledAccountException("该账号未激活，请联系管理员");
        }
    }
}
