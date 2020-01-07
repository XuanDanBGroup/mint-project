package com.tcbd07.mintproject.service;

import com.tcbd07.mintproject.entity.Nd_News;

import java.util.List;

/**
 * @Description
 * @Author 赵泽明
 * @Version 1.0
 */

public interface NewsESService {
    /**
     * es里增加新闻
     * @param news
     * @return
     */
    Nd_News esAddNews(Nd_News news);

    /**
     * es查询所有新闻
     * @return
     */
    List<Nd_News> getAllNews();

    /**
     * es根据新闻标题查询新闻
     * @param title
     * @return
     */
    List<Nd_News> getNewsByTitle(String title);

    /**
     * es根据ID删除ID
     * @param news_id
     */
    void deleteNews(String news_id);


}
