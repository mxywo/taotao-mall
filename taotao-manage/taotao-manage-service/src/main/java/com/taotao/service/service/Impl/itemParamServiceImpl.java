package com.taotao.service.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EUDadaGridResult;
import utils.TaotaoResult;

import java.util.Date;
import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/30 16:29
 */
@Service
public class itemParamServiceImpl implements ItemParamService {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private TbItemParamMapper tbItemParamMapper;

    /**
     * @auther : Mxy 80103005
     * @date : 2018/1/31 10:45
     * @description :查询规格参数信息
     * @param page
     * @param rows
     * @return EUDadaGridResult对象
     */
    @Override
    public EUDadaGridResult getItemParamList(int page, int rows) {
        //确定查询实体
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        //分页处理
        PageHelper.startPage(page,rows);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
        //创建EUGrid对象
        EUDadaGridResult result = new EUDadaGridResult();
        result.setRows(list);
        PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * @auther : Mxy 80103005
     * @date : 2018/1/31 10:59
     * @description :获取规格参数模版By分类Id
     * @param cid
     * @return TaotaoResult ，如果result中的data=null，相当于flag=1 ， 如果data有值，则相当于flag=0
     */
    @Override
    public TaotaoResult getItemParamByCid(long cid) {
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
        if (list.size() > 0 && list != null) {
            return TaotaoResult.ok(list.get(0));
        } else {
            return TaotaoResult.ok(null);
        }
    }

    /**
     * @auther : Mxy 80103005
     * @date : 2018/1/31 14:19
     * @description :保存参数规格模版
     * @param tbItemParam
     * @return TaotaoResult
     */
    @Override
    public TaotaoResult saveItemParam(TbItemParam tbItemParam) {
        //补全pojo
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        tbItemParamMapper.insert(tbItemParam);
        return TaotaoResult.ok();
    }
}
