package com.tcbd07.mintproject.service.impl;

import com.google.common.collect.Lists;
import com.tcbd07.mintproject.dao.NewsESRepository;
import com.tcbd07.mintproject.entity.Nd_News;
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
    public Nd_News esAddNews(Nd_News news) {
        return newsESRepository.save(news);
    }


    @Override
    public List<Nd_News> getAllNews() {
        List<Nd_News> newsList = Lists.newArrayList(newsESRepository.findAll());
        return newsList;
    }

    @Override
    public List<Nd_News> getNewsByTitle(String title) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder=new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("news_title",title));
        List<Nd_News> newsList = Lists.newArrayList(newsESRepository.search(nativeSearchQueryBuilder.build()));

        return newsList;
    }

    @Override
    public void deleteNews(String news_id)  {
            newsESRepository.deleteById(news_id);
    }
}
