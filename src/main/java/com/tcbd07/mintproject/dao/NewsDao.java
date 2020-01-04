package com.tcbd07.mintproject.dao;

import com.tcbd07.mintproject.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NewsDao {
    /**
     * 新闻展示
     * @param title 按标题模糊查询（待定）
     * @return 所有新闻
     */
    List<News> showNews(@Param("title") String title);

    /**
     * 新闻展示
     * @param owner 商家id
     * @return 我的新闻
     */
    List<News> showMyNews(@Param("owner") String owner);

    /**
     * 新闻详情
     * @param id 按id查询单条新闻详情
     * @return 单条新闻信息
     */
    News seleOneNews(@Param("id") Integer id);

    /**
     * 添加新闻
     * @param news 新的新闻信息
     * @return 1成功0失败
     */
    int addNews( News news);

    /**
     * 修改新闻
     * @param news 改后的信息
     * @return 1成功0失败
     */
    int updateNews(News news);

    /**
     * 删除新闻（待定）
     * @param id 新闻id
     * @return 1成功0失败
     */
    int delNews(@Param("id") int id);

    /**
     * 查询标题（单个信息展示的上一篇下一篇）
     * @param id 用id查（查单个时id加减）
     * @return id和标题
     */
    String selTitle(@Param("id")Integer id);
}
