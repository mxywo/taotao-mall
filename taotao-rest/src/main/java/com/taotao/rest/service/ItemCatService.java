package com.taotao.rest.service;

import com.taotao.rest.pojo.CatResult;
import org.springframework.stereotype.Service;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/2/26 10:52
 */
@Service
public interface ItemCatService {
    public CatResult getItemCatList();
    }
