package com.taotao.service.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.EUDadaGridResult;
import utils.HttpClientUtil;
import utils.TaotaoResult;

import java.util.Date;
import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/3/12 15:11
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private TbContentMapper contentMapper;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;


        @Override
        public EUDadaGridResult getContentList(int page,int rows,long categoryId) {
            TbContentExample contentExample = new TbContentExample();
            TbContentExample.Criteria criteria = contentExample.createCriteria();
            criteria.andCategoryIdEqualTo(categoryId);
            PageHelper.startPage(page,rows);
            //紧跟着的第一个select方法会被分页
            List<TbContent> list = contentMapper.selectByExample(contentExample);
            EUDadaGridResult result = new EUDadaGridResult();
            //向对象存值
            result.setRows(list);
            PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
            result.setTotal(pageInfo.getTotal());
            return result;
        }

    @Transactional
    @Override
    public TaotaoResult saveContent(TbContent content) {
        //补全实体中的内容
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        //添加缓存同步逻辑
        try {
            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContent(TbContent content) {
        contentMapper.updateByPrimaryKey(content);

        return TaotaoResult.ok();
    }

    public TaotaoResult deleteContent() {
        return null;
    }
}
