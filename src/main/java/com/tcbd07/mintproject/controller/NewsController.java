package com.tcbd07.mintproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsESRepository newsESRepository;

    @RequestMapping("/")
    public String home(){

        return "template";
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
       String s=JSON.toJSONString(items);
        System.out.println(s);
        map.put("newsList",s);
        map.put("count",count);
        return ResultMessage.success(map);
    }

}
