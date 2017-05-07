package org.weweb.entity.vo.common.vo;

import org.weweb.util.ConstantUtil;

/**
 * Created by jackshen on 16/9/1.
 */
public class PageVo {
    private String startPage= ConstantUtil.startPage;
    private String endPage=ConstantUtil.endPage;

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public String getEndPage() {
        return endPage;
    }

    public void setEndPage(String endPage) {
        this.endPage = endPage;
    }
}
