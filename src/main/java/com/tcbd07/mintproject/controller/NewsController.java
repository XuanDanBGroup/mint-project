package com.tcbd07.mintproject.controller;

import com.tcbd07.mintproject.entity.News;
import com.tcbd07.mintproject.service.LoginService;
import com.tcbd07.mintproject.service.NewsESService;
import com.tcbd07.mintproject.service.NewsService;
import com.tcbd07.mintproject.util.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "新闻展示")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsESService newsESService;
    @Autowired
    private ActiveMQUtils activeMQUtils;

    @Resource
    private RabbitSender rabbitSender;


    @ApiOperation(value = "这是查询新闻功能",notes = "输入新闻名称模糊查询")
    @ApiImplicitParam(name = "title",value = "title",dataType = "String",example = "新闻")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功"),
            @ApiResponse(code = 201, message="啥也不是，就想传一个码")
    })
    @GetMapping("/newsList")
    public ResultMessage newsList(@RequestParam(name = "title",defaultValue = "") String title){
        List<News> newsList=null;

            if(title==null||title.equals((""))){

                newsList=newsESService.getAllNews();
            }else{

                newsList=newsESService.getNewsByTitle(title);
            }

        return ResultMessage.success(newsList);
    }
    @ApiOperation(value = "查询我的新闻",notes = "点击我的新闻进入")
    @ApiImplicitParam(name = "owner",value = "owner",dataType = "String",example = "商家id")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功"),
            @ApiResponse(code = 403,message = "网络异常")
    })
    @GetMapping("/myNews")
    public ResultMessage myNews(String owner){
       List<News> news=newsService.showMyNews(owner);
      return ResultMessage.success(news);
    }
    @ApiOperation(value = "这是查询单个新闻功能",notes = "点击新闻进入单个新闻")
    @ApiImplicitParam(name = "id",value = "id",dataType = "int",example = "123")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功")
    })
    @GetMapping("/OneNews")
    public ResultMessage OneNews(String id){
        News news=newsService.seleOneNews(id);

        return ResultMessage.success(news);
    }

    @ApiOperation(value = "删除新闻",notes = "点击删除键进入")
    @ApiImplicitParam(name = "id",value = "id",dataType = "int",example = "123")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功"),
            @ApiResponse(code = 403,message = "网络异常")
    })
    @GetMapping("/delNews")
    public ResultMessage delNews(String id){
        if(newsService.delNews(id)){

            return ResultMessage.success("200","删除成功！");
        }
        return ResultMessage.success("403","网络异常，请稍候重试！");
    }

    @ApiOperation(value = "这是添加新闻功能",notes = "查询新闻")
    @ApiImplicitParam(name = "news",value = "news",dataType = "News")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "添加成功"),
            @ApiResponse(code = 500,message = "用户不能为空"),
            @ApiResponse(code = 201, message="啥也不是，就想传一个码")
    })
    @PostMapping("/addNews")
    public ResultMessage addNews(News news) throws Exception {
       news.setNews_id(IdWorker.getId());
        if(newsService.addNews(news)){
            rabbitSender.send("addNews");
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
    public ResultMessage updateNews(News news) throws Exception {

        if(newsService.updateNews(news)){
            rabbitSender.send("addNews");
            return ResultMessage.success();
        }else{
            return ResultMessage.error(CommonEnum.USER_NOT_NULL);
        }

    }

}
