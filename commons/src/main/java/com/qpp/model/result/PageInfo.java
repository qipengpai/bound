package com.qpp.model.result;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
/**
 * @author qipengpai
 * @Title: PageInfo
 * @ProjectName bound
 * @Description: TODO 分页返回类型
 * @date 9:44 2018/10/12
 */
@Data
@SuppressWarnings({"rawtypes", "unchecked"})
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageNum;
    //每页的数量
    private int pageSize = 10;
    //总记录数
    private long total;
    //总页数
    private int pages;
    //结果集
    private List<T> resultSet;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;

    //开始时间
    private String startDate;
    //结束时间
    private String endDate;

    /**
     * @Fields responseCode : TODO(响应状态码)
     */
    private String responseCode;

    /**
     * @Fields responseMessage : TODO(响应码描述)
     */
    private String responseMessage;
    /**
     * 标识是否成功调用 true 成功 false 失败
     */
    private String exceptionFlag;

    public PageInfo() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.pages = page.getPages();
            this.resultSet = page;
            this.total = page.getTotal();
        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.pages = 1;
            this.resultSet = list;
            this.total = list.size();
        }
        if (list instanceof Collection) {
            //判断页面边界
            judgePageBoudary();
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages;
    }



    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", resultSet=").append(resultSet);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", navigatepageNums=");
        sb.append('}');
        return sb.toString();
    }
}
