package com.cms.portal.freemarker.directive;

import com.cms.service.api.CmsFriendLinkService;
import com.cms.service.dto.CmsFriendLinkDto;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author jayy
 * @Description 友情链接freemarker自定义指令
 * @Date 2020/11/17 18:42
 * @Version 1.0
 */
public class CmsFriendLinkDirective implements TemplateDirectiveModel {

    @Autowired
    CmsFriendLinkService cmsFriendLinkService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        List<CmsFriendLinkDto> list = cmsFriendLinkService.getList();
        // templateModels[0] = new SimpleSequence(list, null);
        environment.setVariable("result",new DefaultObjectWrapper(Configuration.VERSION_2_3_28).wrap(list));
        templateDirectiveBody.render(environment.getOut());
    }
}
