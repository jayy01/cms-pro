package com.cms.portal.security.realm;

import com.cms.dao.enums.UserStatusEnum;
import com.cms.service.api.CmsUserPrimartService;
import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserDto;
import com.cms.service.dto.CmsUserPrimaryDto;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

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
    CmsUserPrimartService cmsUserPrimartService;

    /**
     * 登录信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
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
        //String password = (String) authenticationToken.getCredentials();
        // 副表中查找用户是否存在
        CmsUserDto cmsUserDto = cmsUserService.selectByUsername(username);
        if(Objects.isNull(cmsUserDto)){
            throw new UnknownAccountException();
        }
        // 校验用户状态 是否禁用
        verifyStatus(cmsUserDto.getStatus());
        // 查询用户的主表信息  主表父表id相同通过id查询
        CmsUserPrimaryDto cmsUserPrimaryDto = cmsUserPrimartService.getById(cmsUserDto.getId());
        //比对密码
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(cmsUserDto, cmsUserPrimaryDto.getPassword(),
                ByteSource.Util.bytes(cmsUserPrimaryDto.getSalt()), getName());
        // 清除认证信息
        super.clearCachedAuthenticationInfo(simpleAuthenticationInfo.getPrincipals());
        return simpleAuthenticationInfo;
    }

    /**
     * 验证用户状态
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
