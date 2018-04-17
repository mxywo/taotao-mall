package com.taotao.controller;

import com.taotao.pojo.TbContent;
import com.taotao.service.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EUDadaGridResult;
import utils.TaotaoResult;

import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/3/12 11:34
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EUDadaGridResult getContentList(int page, int rows, long categoryId){
        return contentService.getContentList(page, rows, categoryId);
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult saveContent(TbContent content) {
        return contentService.saveContent(content);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public TaotaoResult editContent(TbContent content) {
        return contentService.updateContent(content);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContent(@RequestParam("idList") List<Long> idList) {
        return null;
    }
}
