package com.taotao.portal.service.Impl;

import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.HttpClientUtil;
import utils.JsonUtils;
import utils.TaotaoResult;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/3/29 16:07
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;


    @Override
    public String getContentList() {
        //调用服务层的服务
        String s = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
        //把得到的结果转化成taotaoResult对象
        try {
            TaotaoResult taotaoResult = TaotaoResult.formatToList(s, TbContent.class);
            //取内容列表
            List<TbContent> list = (List<TbContent>) taotaoResult.getData();
            //创建一个jsp页面要求的pojo列表
            List<Map> resultList = new LinkedList<>();
            for (TbContent tbContent : list) {
                Map map = new HashMap<>();
                map.put("height", 240);
                map.put("width", 670);
                map.put("src", tbContent.getPic());
                map.put("heightB", 240);
                map.put("widthB", 550);
                map.put("srcB", tbContent.getPic2());
                map.put("href", tbContent.getUrl());
                map.put("alt", tbContent.getSubTitle());
                resultList.add(map);
            }
            return JsonUtils.objectToJson(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
