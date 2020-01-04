package com.tcbd07.mintproject.controller;

import com.alibaba.fastjson.JSON;
import com.tcbd07.mintproject.dao.NewsESRepository;
import com.tcbd07.mintproject.entity.News;
import com.tcbd07.mintproject.service.NewsService;
import com.tcbd07.mintproject.util.ActiveMQUtils;
import com.tcbd07.mintproject.util.CommonEnum;
import com.tcbd07.mintproject.util.ResultMessage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsESRepository newsESRepository;
    @Autowired
    private ActiveMQUtils activeMQUtils;

    @RequestMapping("/")
    public String home(){

        return "login";
    }

    /**
     * 查询新闻
     * @param key
     * @return
     */
    @ApiOperation(value = "这是查询新闻功能",notes = "输入新闻名称模糊查询")
    @ApiImplicitParam(name = "key",value = "key",dataType = "String",example = "新闻")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功"),
            @ApiResponse(code = 201, message="啥也不是，就想传一个码")
    })
    @GetMapping("/newsList")
    public ResultMessage newsList(String key){
        NativeSearchQueryBuilder nativeSearchQueryBuilder=new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("news_title",key));
        Page<News> items=newsESRepository.search(nativeSearchQueryBuilder.build());
        Long count=items.getTotalElements();
        Map<String ,Object>  map=new HashMap<>();
       String s= JSON.toJSONString(items);
        System.out.println(s);
        map.put("newsList",s);
        map.put("count",count);
        return ResultMessage.success(map);
    }

    @ApiOperation(value = "这是添加新闻功能",notes = "查询新闻")
    @ApiImplicitParam(name = "news",value = "news",dataType = "News")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "添加成功"),
            @ApiResponse(code = 500,message = "用户不能为空"),
            @ApiResponse(code = 201, message="啥也不是，就想传一个码")
    })
    @PostMapping("/addNews")
    public ResultMessage addNews(News news){

        if(newsService.addNews(news)){
            activeMQUtils.sendQueueMesage("addNews","请求添加新闻");

            return ResultMessage.success();
        }else{
            return ResultMessage.error(CommonEnum.USER_NOT_NULL);
        }

    }

    @ApiOperation(value = "这是修改新闻功能",notes = "修改新闻")
    @ApiImplicitParam(name = "news",value = "news",dataType = "News")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "修改成功"),
            @ApiResponse(code = 500,message = "用户不能为空"),
            @ApiResponse(code = 201, message="啥也不是，就想传一个码")
    })
    @PostMapping("/updateNews")
    public ResultMessage updateNews(News news){


        if(newsService.updateNews(news)){
            activeMQUtils.sendQueueMesage("updateNews","请求修改新闻");

            return ResultMessage.success();
        }else{
            return ResultMessage.error(CommonEnum.USER_NOT_NULL);
        }

    }

}
