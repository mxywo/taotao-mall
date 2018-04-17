package com.taotao.service.service;

import pojo.EUTreeNode;

import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/4 11:14
 */
public interface ItemCatService {
    public List<EUTreeNode> getItemCatList(long parentId);
}
