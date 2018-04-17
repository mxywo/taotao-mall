package com.taotao.rest.controller;

import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.TaotaoResult;

/**
 * @Auther : mengxiaoyang@crscd.com.cn
 * @Description : 服务层提供的Redis调用
 * @Date : Creat in 2018/4/8 8:43
 * @Modified By :
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {
    @Autowired
    private RedisService redisService;


    @RequestMapping("/contentDel/{contentCatId}")
    @ResponseBody
    public TaotaoResult contentCacheDelSync(@PathVariable("contentCatId") long contentCatId) {
        return redisService.syncDelContent(contentCatId);
    }
}
