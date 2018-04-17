package com.taotao.portal.controller;

import com.taotao.portal.service.Impl.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.TaotaoResult;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/2/8 10:55
 */
@Controller
public class IndexController {
    @Autowired
    private ContentServiceImpl contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        String adJson = contentService.getContentList();
        model.addAttribute("ad1", adJson);
        return "index";
    }

    @RequestMapping(value = "/httpclient/post", method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody
    public String testPost(String username, String pwd) {
//        return TaotaoResult.ok();
        System.out.println("username:" + username + "  pwd:" + pwd);
        return "username:" + username + "  pwd:" + pwd;
    }
}
