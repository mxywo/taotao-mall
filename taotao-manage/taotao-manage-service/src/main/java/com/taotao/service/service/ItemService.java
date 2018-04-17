package com.taotao.service.service;


import com.taotao.pojo.TbItem;
import pojo.EUDadaGridResult;
import utils.TaotaoResult;

public interface ItemService {

	TbItem getItemById(long itemId);
	EUDadaGridResult getItemList(int page,int rows);

	TaotaoResult addItem(TbItem tbItem, String desc, String itemParam) throws Exception;

	TaotaoResult addItemDesc(Long id, String desc);

	TaotaoResult addItemParamItem(Long id, String itemParam);

//	TaotaoResult deleteItem(Long id);
}
