package com.taotao.service.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.*;
import com.taotao.service.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EUDadaGridResult;
import pojo.EUTreeNode;
import utils.TaotaoResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/2/27 9:35
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private TbContentCategoryMapper contentCategoryMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private TbContentMapper contentMapper;

    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        //根据parentId查询
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EUTreeNode treeNode = new EUTreeNode();
            treeNode.setId(tbContentCategory.getId());
            treeNode.setText(tbContentCategory.getName());
            treeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
            resultList.add(treeNode);
        }
        return resultList;
    }

    @Override
    public TaotaoResult addContentCategory(long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setName(name);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        //添加记录
        contentCategoryMapper.insert(tbContentCategory);//插入后，主键id自动返回，pojo中的id被赋值
        //查看父节点的isParent是否为true，如果不是就将其值改为true
        TbContentCategory tbContentCategory1 = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!tbContentCategory1.getIsParent()){
            tbContentCategory1.setIsParent(true);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(tbContentCategory1);
        }
        return TaotaoResult.ok(tbContentCategory);
    }

    @Override
    public TaotaoResult deleteContentCategory(long parentId, long Id) {
        contentCategoryMapper.deleteByPrimaryKey(Id);
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        if (list==null && list.size()==0) {
            TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
            tbContentCategory.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(tbContentCategory);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContentCategory(String name, long Id) {
        TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(Id);
        tbContentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKey(tbContentCategory);
        return TaotaoResult.ok(tbContentCategory);
    }
}
