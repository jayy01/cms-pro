package com.cms.portal.security.realm;

import com.cms.service.api.CmsUserService;
import com.cms.service.converter.CmsUserPrimaryConverter;
import com.cms.service.dto.CmsUserPrimaryDto;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author jayy
 * @Description
 * @Date 2020/9/28 14:41
 * @Version 1.0
 */
public class UsernamePasswordCaptchaRealm extends AuthorizingRealm {

    @Autowired
    CmsUserService cmsUserService;

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

        String username = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();
        CmsUserPrimaryDto cmsUserPrimaryDto = cmsUserService.selectByUsername(username);



        return null;
    }
}
