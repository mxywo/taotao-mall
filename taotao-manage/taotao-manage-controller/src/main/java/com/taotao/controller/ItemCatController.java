package com.taotao.controller;

import com.taotao.service.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EUTreeNode;

import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/4 14:17
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    private List<EUTreeNode> getItemCatList(@RequestParam(value="id", defaultValue="0")long parentId){
        //树控件读取URL。子节点的加载依赖于父节点的状态。当展开一个封闭的节点，如果节点没有加载子节点，
        //它将会把节点id的值作为http请求参数并命名为'id'，通过URL发送到服务器上面检索子节点。
        //so,http请求定义的参数名不是parentId而是id，所以需要用RequestParam注解
        List<EUTreeNode> list = itemCatService.getItemCatList(parentId);
        return list;
    }
}
