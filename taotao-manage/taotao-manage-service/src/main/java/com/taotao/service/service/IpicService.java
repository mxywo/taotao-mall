package com.taotao.service.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/12 16:26
 */
public interface IpicService {
    Map uploadPic(MultipartFile uploadfile);
}
