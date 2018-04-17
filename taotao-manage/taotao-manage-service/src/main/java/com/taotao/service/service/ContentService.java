package com.taotao.service.service;

import com.taotao.pojo.TbContent;
import pojo.EUDadaGridResult;
import utils.TaotaoResult;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/3/12 11:37
 */
public interface ContentService {
    EUDadaGridResult getContentList(int page, int rows, long categoryId);

    TaotaoResult saveContent(TbContent tbContent);

    TaotaoResult updateContent(TbContent content);
}
