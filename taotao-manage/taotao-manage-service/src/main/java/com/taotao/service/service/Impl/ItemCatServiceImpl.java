package com.taotao.service.service.Impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EUTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/4 11:16
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * @auther : Mxy 80103005
     * @date : 2018/1/4 14:14
     * @description :获取目录树节点信息，轮询方式：点一次下拉三角，查询一次。通过isParent判断叶子与否
     * closed:父节点；open:叶子节点
     * @param parentId
     * @return
     */
    @Override
    public List<EUTreeNode> getItemCatList(long parentId) {
        //根据parentId查询分类列表
        TbItemCatExample example = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //分类列表转换成TreeNode的列表
        List<EUTreeNode> treeNodeList = new ArrayList<>();
        for (TbItemCat t : list){
            EUTreeNode node = new EUTreeNode(t.getId(),t.getName(),t.getIsParent()?"closed":"open");
            //如果该表达式为真,则表达式的结果为b,如果表达式为假,则为c的结果
            treeNodeList.add(node);
        }
//        for(TbItemCat cat : list) {
//            criteria.andIdEqualTo(cat.getId());
//            criteria.andNameEqualTo(cat.getName());
//
//        }
        return treeNodeList;
    }
}
