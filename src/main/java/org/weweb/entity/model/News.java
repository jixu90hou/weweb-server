package org.weweb.entity.model;

import java.util.Date;
import javax.persistence.*;

public class News {
    /**
     * 新闻ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 新闻主题
     */
    private String title;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 新闻内容
     */
    private String content;

    /**
     * 获取新闻ID
     *
     * @return id - 新闻ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置新闻ID
     *
     * @param id 新闻ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取新闻主题
     *
     * @return title - 新闻主题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置新闻主题
     *
     * @param title 新闻主题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取新闻内容
     *
     * @return content - 新闻内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置新闻内容
     *
     * @param content 新闻内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}