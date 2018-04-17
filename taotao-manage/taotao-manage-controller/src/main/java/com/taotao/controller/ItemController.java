package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EUDadaGridResult;
import utils.TaotaoResult;


@Controller
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@RequestMapping(value = "/item/{itemId}",method = RequestMethod.POST)
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EUDadaGridResult getItemList(int page,int rows){
		EUDadaGridResult result = itemService.getItemList(page,rows);
		return result;
	}

	@RequestMapping(value = "/item/save",method = RequestMethod.POST)
	@ResponseBody
	private TaotaoResult addItem(TbItem tbItem , String desc ,String itemParam) throws Exception {
		TaotaoResult result = itemService.addItem(tbItem,desc,itemParam);
		return result;
	}

//	@RequestMapping(value = "item/delete",method = RequestMethod.POST)
//	@ResponseBody
//	public TaotaoResult deleteItemById(@PathVariable TbItem tbItem) {
//		TaotaoResult result = itemService.deleteItem(itemId);
//		return result;
//	}
}
