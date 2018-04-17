package com.taotao.rest.service.Impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/2/26 10:52
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ITEM_CAT_REDIS_KEY}")
    private String ITEM_CAT_REDIS_KEY;

    @Override
    public CatResult getItemCatList() {

        CatResult catResult = new CatResult();
        //查询分类列表
        catResult.setData(getCatList(0));
        return catResult;
    }

    /**
     * 查询分类列表
     * <p>Title: getCatList</p>
     * <p>Description: </p>
     * @param parentId
     * @return
     */
    private List<?> getCatList(long parentId) {

//        //从缓存中获取内容，若有异常，继续执行下面的功能块
//        try {
//            String hget = jedisClient.hget(ITEM_CAT_REDIS_KEY, String.valueOf(parentId));
//            if (!StringUtils.isBlank(hget)) {
//                List<TbItemCat> redisResult = JsonUtils.jsonToList(hget, TbItemCat.class);
//                return redisResult;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //返回值list
        List resultList = new ArrayList<>();
        //向list中添加节点
        int count = 0;
        for (TbItemCat tbItemCat : list) {
            //判断是否为父节点
            if (tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                } else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/"+tbItemCat.getId()+".html");
                catNode.setItem(getCatList(tbItemCat.getId()));
                resultList.add(catNode);
                count++;
                //第一层类目只取14条
                if (parentId == 0 && count >= 14) {
                    break;
                }

                //如果是叶子节点
            } else {
                resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
            }
        }

//        //向缓存中添加内容，若有异常，继续返回取出的list
//        //把list转换成字符串
//        try {
//            String s = JsonUtils.objectToJson(resultList);
//            jedisClient.hset(ITEM_CAT_REDIS_KEY, String.valueOf(parentId), s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return resultList;
    }

}
