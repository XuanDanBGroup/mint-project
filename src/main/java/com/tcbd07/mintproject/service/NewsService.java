package com.tcbd07.mintproject.service;

import com.tcbd07.mintproject.entity.News;


import java.util.List;

public interface NewsService {
    /**
     * 新闻展示
     * @param title 按标题模糊查询（待定）
     * @return 所有新闻
     */
    List<News> showNews( String title);

    /**
     * 新闻详情
     * @param id 按id查询单条新闻详情
     * @return 单条新闻信息
     */
    News seleOneNews( Integer id);

    /**
     * 添加新闻
     * @param news 新的新闻信息
     * @return 1成功0失败
     */
    boolean addNews( News news);

    /**
     * 修改新闻
     * @param news 改后的信息
     * @return 1成功0失败
     */
    boolean updateNews(News news);

    /**
     * 删除新闻（待定）
     * @param id 新闻id
     * @return 1成功0失败
     */
    boolean delNews( int id);
}
