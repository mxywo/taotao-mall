package com.taotao.rest.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/3/29 9:32
 */
public interface ContentService {
    List<TbContent> getContentList(long contentId);
}
