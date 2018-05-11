package com.easybuild.cores.utils;
/**
 * 
 * <p>
 *   分页工具类
 * </p>
 *
 *
 */
public class PageBean {

    private int page; // 页码
    private int pageSize; // 单页数据量
    private int start;//开始位置

    public PageBean(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

	public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
       this.start= (page - 1) * pageSize;
       return start;
    }

}