package com.taotao.service.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EUDadaGridResult;
import utils.IDUtils;
import utils.TaotaoResult;

import java.util.Date;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private TbItemMapper itemMapper;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private TbItemDescMapper itemDescMapper;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		
		TbItemExample tbItemExample = new TbItemExample();
		TbItemExample.Criteria criteria = tbItemExample.createCriteria();
		criteria.andIdEqualTo(itemId);// where + (id = itemId)
		List<TbItem> list = itemMapper.selectByExample(tbItemExample);//select * from TbItem where id = itemId
		// 没有实现类是和mybatis的封装原理有关
		if(list!=null && list.size()>0) {
			TbItem tbItem = list.get(0);
			return tbItem;
		}
		return null;
	}

	/**
	 * @auther : Mxy 80103005
	 * @date : 2018/1/3 13:53
	 * @description :商品列表查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public EUDadaGridResult getItemList(int page, int rows) {
		//确定查询实体
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page,rows);
		//紧跟着的第一个select方法会被分页
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建返回值对象
		EUDadaGridResult result = new EUDadaGridResult();
		//向对象存值
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult addItem(TbItem tbItem, String desc, String itemParam) throws Exception {
		//tbItem补全
		//生成商品ID，随机数
		Long itemId = IDUtils.genItemId();
		tbItem.setId(itemId);
		//商品状态,1-正常，2-下架，3-删除
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		//商品基本信息插入数据库
		itemMapper.insert(tbItem);
		//商品详情插入
		TaotaoResult result = addItemDesc(itemId, desc);//此处的Id正好是上面生成的
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		result = addItemParamItem(itemId, itemParam);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		return TaotaoResult.ok();
	}

	/**
	 * 要将商品详情和商品基本信息一起插入时（两张表），要保证在同一个事物中，在配置事物时，切面在 Service，
	 * 应该在 Service中进行插入操作，而不能在 Controller中
	 * @param id
	 * @param desc
	 * @return
	 */
	@Override
	public TaotaoResult addItemDesc(Long id ,String desc) {
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(id);
		tbItemDesc.setUpdated(new Date());
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setItemDesc(desc);
		//插入
		itemDescMapper.insert(tbItemDesc);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult addItemParamItem(Long id, String itemParam) {
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setItemId(id);
		tbItemParamItem.setParamData(itemParam);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());

		itemParamItemMapper.insert(tbItemParamItem);
		return TaotaoResult.ok();
	}

//	@Override
//	public TaotaoResult deleteItem(TbItem tbItem) {
//		TbItemExample example = new TbItemExample();
//		TbItemExample.Criteria criteria = example.createCriteria();
//		itemMapper.deleteByPrimaryKey(id);
//		return TaotaoResult.ok();
//	}
}
