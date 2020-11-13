package com.cms.core.foundation;

import java.io.Serializable;
import java.util.List;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/11 14:26
 * @Version 1.0
 */
public class BasePage<T> implements Serializable {
    /**
     * 分页条数
     */
    private Long pageCount;
    /**
     * 内容
     */
    private List<T> content;

    public BasePage(Long pageCount, List<T> content) {
        this.pageCount = pageCount;
        this.content = content;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
