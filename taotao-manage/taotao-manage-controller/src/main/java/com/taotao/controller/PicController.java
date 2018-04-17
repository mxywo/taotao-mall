package com.taotao.controller;

import com.taotao.service.service.IpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import utils.JsonUtils;

import java.util.Map;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/22 14:31
 */

@Controller
public class PicController {
    @Autowired
    private IpicService picService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String picUpload(MultipartFile uploadFile){
        Map result = picService.uploadPic(uploadFile);
        String json = JsonUtils.objectToJson(result);
        return json;
    }
}
