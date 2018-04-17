package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2017/12/29 14:55
 * @auther : Mxy 80103005
 * @date : 2017/12/29 15:00
 * @description :页面跳转controller
 */
@Controller
public class PageController {
    /**
     * @auther : Mxy 80103005
     * @date : 2017/12/29 15:00
     * @description :打开首页
     * @return
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";//原因：在springmvc.xml中配置了视图解析器，自动加上路径的前缀和后缀
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
