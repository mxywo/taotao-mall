package com.taotao.service.service;

import com.taotao.pojo.TbItemParam;
import pojo.EUDadaGridResult;
import utils.TaotaoResult;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/30 16:29
 */
public interface ItemParamService {
    EUDadaGridResult getItemParamList(int page, int rows);

    TaotaoResult getItemParamByCid(long cid);

    TaotaoResult saveItemParam(TbItemParam tbItemParam);
}
