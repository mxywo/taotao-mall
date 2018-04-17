package com.taotao.rest.service;

import utils.TaotaoResult;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/4/7 16:34
 */
public interface RedisService {

    TaotaoResult syncDelContent(long contentCatId);

}
