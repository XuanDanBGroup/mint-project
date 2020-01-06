package com.tcbd07.mintproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.tcbd07.mintproject.entity.Nd_News;
import com.tcbd07.mintproject.entity.User;
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
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private RedisUtil redisUtil;
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
        List<Nd_News> newsList=null;

            if(EmptyUtils.isEmpty(title)){

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
       List<Nd_News> news=newsService.showMyNews(owner);
      return ResultMessage.success(news);
    }
    @ApiOperation(value = "这是查询单个新闻功能",notes = "点击新闻进入单个新闻")
    @ApiImplicitParam(name = "id",value = "id",dataType = "int",example = "123")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功")
    })
    @GetMapping("/OneNews")
    public ResultMessage OneNews(String id){
        Nd_News news=newsService.seleOneNews(id);

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "title",dataType = "String"),
            @ApiImplicitParam(name = "content",value = "content",dataType = "String"),

    })

    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "添加成功"),
            @ApiResponse(code = 500,message = "用户不能为空"),
            @ApiResponse(code = 201, message="啥也不是，就想传一个码")
    })
    @PostMapping("/addNews")
    public ResultMessage addNews(String title, String content, HttpServletRequest request) throws Exception {
        String token=request.getHeader("token");
        if(EmptyUtils.isEmpty(token)){
            return ResultMessage.error("404","用户未登录");
        }else{
            Nd_News news=new Nd_News();
            String userJson=redisUtil.getStr(token);
            User user= JSONObject.parseObject(userJson,User.class);
            news.setNews_content(content);
            news.setNews_title(title);
            news.setNews_owner(user.getUserId());
            news.setNews_id(IdWorker.getId());
            if(newsService.addNews(news)){
                rabbitSender.send("addNews");
                return ResultMessage.success();
            }else{
                return ResultMessage.error(CommonEnum.USER_NOT_NULL);
            }
        }



    }

    @ApiOperation(value = "这是修改新闻功能",notes = "修改新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "title",dataType = "String",example = "新闻标题"),
            @ApiImplicitParam(name = "content",value = "content",dataType = "String",example = "新闻内容"),
            @ApiImplicitParam(name = "news_id",value = "news_id",dataType = "String",example = "修改的新闻ID"),

    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "修改成功"),
            @ApiResponse(code = 500,message = "用户不能为空"),
            @ApiResponse(code = 201, message="啥也不是，就想传一个码")
    })
    @PostMapping("/updateNews")
    public ResultMessage updateNews(String title, String content,String news_id) throws Exception {
        Nd_News news=new Nd_News();
        news.setNews_content(content);
        news.setNews_title(title);
        news.setNews_id(news_id);
        if(newsService.updateNews(news)){
            rabbitSender.send("addNews");
            return ResultMessage.success();
        }else{
            return ResultMessage.error(CommonEnum.USER_NOT_NULL);
        }

    }

}
