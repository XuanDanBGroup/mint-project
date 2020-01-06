package com.tcbd07.mintproject.service.impl;

import com.google.common.collect.Lists;
import com.tcbd07.mintproject.dao.NewsESRepository;
import com.tcbd07.mintproject.entity.News;
import com.tcbd07.mintproject.service.NewsESService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description
 * @Author 赵泽明
 * @Version 1.0
 */
@Component
public class NewsESServiceImpl implements NewsESService {

    @Autowired
    private NewsESRepository newsESRepository;



    @Override
    public News esAddNews(News news) {
        return newsESRepository.save(news);
    }


    @Override
    public List<News> getAllNews() {
        List<News> newsList = Lists.newArrayList(newsESRepository.findAll());
        return newsList;
    }

    @Override
    public List<News> getNewsByTitle(String title) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder=new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("title",title));
        List<News> newsList = Lists.newArrayList(newsESRepository.search(nativeSearchQueryBuilder.build()));

        return newsList;
    }

    @Override
    public void deleteNews(String news_id)  {
            newsESRepository.deleteById(news_id);
    }
}
