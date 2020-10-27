package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author jayy
 * @Description
 * @Date 2020/10/13 14:59
 * @Version 1.0
 */
@Getter
@Setter
public class CmsLogDto extends BaseDto<Integer> {

    private Integer userId;
    private String username;
    private String loginIp;
    private String url;
    private String content;
    private LocalDateTime createTime;

    /**
     * 封装对象创建
     * @param username
     * @param loginIp
     * @param url
     * @param content
     * @return CmsLogDto
     */
    public static CmsLogDto of(Integer userId,String username,String loginIp,String url,String content){
        CmsLogDto cmsLogDto = new CmsLogDto();
        cmsLogDto.setContent(content);
        cmsLogDto.setLoginIp(loginIp);
        cmsLogDto.setUrl(url);
        cmsLogDto.setUserId(userId);
        cmsLogDto.setUsername(username);
        return cmsLogDto;
    }


}
