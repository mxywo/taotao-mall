package com.taotao.rest.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.ExceptionUtil;
import utils.TaotaoResult;

import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/3/29 9:36
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    /**
     * @auther : Mxy 80103005
     * @date : 2018/4/7 12:42
     * @description :首页大广告位的展示
     * @param contentCategoryId
     * @return
     */
    @RequestMapping(value = "/list/{contentCategoryId}" , method = RequestMethod.GET)
    @ResponseBody
    TaotaoResult getContentList(@PathVariable Long contentCategoryId) {
        List<TbContent> list = null;
        try {
            list = contentService.getContentList(contentCategoryId);
            return TaotaoResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
