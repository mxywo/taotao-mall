package com.taotao.controller;

import com.taotao.pojo.TbItemParam;
import com.taotao.service.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EUDadaGridResult;
import utils.TaotaoResult;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/30 16:41
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ItemParamService itemParamService;


    /**
     * @auther : Mxy 80103005
     * @date : 2018/1/31 15:49
     * @description :规格参数页
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public EUDadaGridResult getParamList(int page, int rows) {
        return itemParamService.getItemParamList(page, rows);
    }


    /**
     * @auther : Mxy 80103005
     * @date : 2018/1/31 15:50
     * @description :获取规格参数模版By分类Id，并验证模版是否已存在
     * @param itemCatId
     * @return
     */
    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getParamByCid(@PathVariable long itemCatId) {
        return itemParamService.getItemParamByCid(itemCatId);
    }

    /**
     * @auther : Mxy 80103005
     * @date : 2018/1/31 15:51
     * @description :新增商品规格模版
     * @param cid
     * @param paramData
     * @return
     */
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult saveParam(@PathVariable long cid, String paramData) {
        //创建pojo对象
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        return itemParamService.saveItemParam(tbItemParam);
    }
}
