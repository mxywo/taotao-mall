package com.taotao.service.service.Impl;

import com.taotao.service.service.IpicService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.FtpUtil;
import utils.IDUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传服务
 *
 * @auther Mxy 80103005
 * @date : Creat in 2018/1/12 16:27
 */
@Service
public class picServiceImpl implements IpicService {
    @Value("${FTP_IP}")
    private String FTP_IP;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USER}")
    private String FTP_USER;
    @Value("${FTP_PWD}")
    private String FTP_PWD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    /**
     * @param uploadfile 接收从前台controller传来的的参数
     * @return json，包含成功和失败两种情况
     * 成功：error和url；失败：error和message
     * @auther : Mxy 80103005
     * @date : 2018/1/22 14:02
     * @description :接收从前台传来的的MultiPartFile，生成一个新文件名，传到ftp服务器，返回文件url路径
     */
    @Override
    public Map uploadPic(MultipartFile uploadfile) {
        Map resultmap = new HashMap<>();
        //生成一个新的文件名
        //取原始文件名
        String oldName = uploadfile.getOriginalFilename();
        //生成新文件名，避免重复
        // UUID，名字随机，但是很长       UUID.randomUUID();
        String newName = IDUtils.genImageName();
        newName = newName + oldName.substring(oldName.lastIndexOf("."));
        String imagePath = new DateTime().toString("/yyyyMMdd");
        //定义属性文件，供项目使用
        try {
            boolean result = FtpUtil.uploadFile(FTP_IP, FTP_PORT, FTP_USER, FTP_PWD, FTP_BASE_PATH, imagePath
                    , newName, uploadfile.getInputStream());
            if (!result) {
                resultmap.put("error", 1);
                resultmap.put("message", "上传失败");
                return resultmap;
            } else {
                resultmap.put("error", 0);
                resultmap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
                return resultmap;
            }
        } catch (IOException e) {
            resultmap.put("error", 1);
            resultmap.put("message", "ftp上传异常");
            return resultmap;
        }
    }
}
