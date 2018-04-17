package com.taotao.service.service;

import pojo.EUDadaGridResult;
import pojo.EUTreeNode;
import utils.TaotaoResult;

import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/2/27 9:35
 */
public interface ContentCategoryService {
    public List<EUTreeNode> getCategoryList(long parentId);
    TaotaoResult addContentCategory(long parentId , String name);

    TaotaoResult deleteContentCategory(long parentId, long Id);

    TaotaoResult updateContentCategory(String name, long Id);
}
