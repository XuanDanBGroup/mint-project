package com.tcbd07.mintproject.service.impl;

import com.tcbd07.mintproject.dao.newsDao;
import com.tcbd07.mintproject.entity.News;
import com.tcbd07.mintproject.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl  implements NewsService {
    @Resource
    newsDao dao;
    @Override
    public List<News> showNews(String title) {
        return dao.showNews(title);
    }

    @Override
    public News seleOneNews(Integer id) {
        return dao.seleOneNews(id);
    }

    @Override
    public boolean addNews(News news) {
        if(dao.addNews(news)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateNews(News news) {
        if(dao.updateNews(news)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delNews(int id) {
        if(dao.delNews(id)>0){
            return true;
        }
        return false;
    }
}
