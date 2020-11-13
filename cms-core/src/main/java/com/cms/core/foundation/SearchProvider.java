package com.cms.core.foundation;

/**
 * @Author jayy
 * @Description
 * @Date 2020/11/12 13:42
 * @Version 1.0
 */
public class SearchProvider<T extends BaseEntity> {
    /**
     * 查询条件
     */
    private T criteria;
    /**
     * 排序字段
     */
    private String searchOrderBy;

    /**
     * 静态方法  不排序
     * @param criteria
     * @param <W>
     * @return
     */
    public static<W extends BaseEntity>  SearchProvider of(W criteria){
        SearchProvider searchProvider = new SearchProvider();
        searchProvider.setCriteria(criteria);
        return searchProvider;
    }

    /**
     * 静态方法  带排序
     * @param criteria
     * @param searchOrderBy
     * @param <W>
     * @return
     */
    public static<W extends BaseEntity>  SearchProvider of(W criteria,String searchOrderBy){
        SearchProvider searchProvider = new SearchProvider();
        searchProvider.setCriteria(criteria);
        searchProvider.setSearchOrderBy(searchOrderBy);
        return searchProvider;
    }

    public T getCriteria() {
        return criteria;
    }

    public void setCriteria(T criteria) {
        this.criteria = criteria;
    }

    public String getSearchOrderBy() {
        return searchOrderBy;
    }

    public void setSearchOrderBy(String searchOrderBy) {
        this.searchOrderBy = searchOrderBy;
    }
}
