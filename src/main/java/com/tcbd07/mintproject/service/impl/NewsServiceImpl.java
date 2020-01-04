package com.tcbd07.mintproject.service.impl;

import com.tcbd07.mintproject.dao.NewsDao;
import com.tcbd07.mintproject.entity.News;
import com.tcbd07.mintproject.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl  implements NewsService {
    @Resource
    private NewsDao dao;
    @Override
    public List<News> showNews(String title) {
        return dao.showNews(title);
    }

    @Override
    public List<News> showMyNews(String owner) {
        return dao.showMyNews(owner);
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

    @Override
    public String selTitle(Integer id) {
        return dao.selTitle(id);
    }
}
