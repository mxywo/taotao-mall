package com.taotao.controller;

import com.taotao.service.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/2/5 9:55
 */
@Controller
public class ItemParamItemController {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/showItem/{itemId}")
    public String getItemParamItemById(@PathVariable Long itemId, Model model) {
        String itemParamItem = itemParamItemService.geParamItemByItemId(itemId);
        model.addAttribute("itemParam", itemParamItem);
        return "item-param-show";
    }
}
