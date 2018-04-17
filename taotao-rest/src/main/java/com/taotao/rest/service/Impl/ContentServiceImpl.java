package com.taotao.rest.service.Impl;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.JsonUtils;

import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/3/29 9:32
 */
@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private TbContentMapper mapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    /**
     * @auther : Mxy 80103005
     * @date : 2018/3/29 9:36
     * @description :根据内容分类Id查询内容列表
     * @param contentCategoryId
     * @return
     */
    @Override
    public List<TbContent> getContentList(long contentCategoryId) {
        //从缓存中获取内容，若有异常，继续执行下面的功能块
        try {
            String hget = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentCategoryId));
            if (!StringUtils.isBlank(hget)) {
                List<TbContent> redisResult = JsonUtils.jsonToList(hget, TbContent.class);
                return redisResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

            //根据内容分类Id查询内容列表
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(contentCategoryId);
            List<TbContent> list = mapper.selectByExample(example);

            //向缓存中添加内容，若有异常，继续返回取出的list
            //把list转换成字符串
            try {
                String s = JsonUtils.objectToJson(list);
                jedisClient.hset(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentCategoryId), s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return list;
    }
}
