package com.taotao.rest.service.Impl;

import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.ExceptionUtil;
import utils.TaotaoResult;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/4/7 16:44
 * @Description : 用于后台对Redis内容进行操作
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;


    /**
     *  @auther : Mxy 80103005
     * @date : 2018/4/8 8:34
     * @description : 后台删除一个内容时，去Redis中同步删除那个ID
     * @param contentCatId
     * @return
     */
    @Override
    public TaotaoResult syncDelContent(long contentCatId) {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentCatId));
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
}
