package com.igeek.egobuy.egobuyfile.controller;

import com.google.gson.Gson;
import com.igeek.egobuy.exception.CustomException;
import com.igeek.egobuy.exception.CustomExceptionType;
import com.igeek.egobuy.util.ResponseEntity;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 余祥刚
 * @create 2020-03-02 21:00
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @RequestMapping("/item")
    public ResponseEntity fileUpload(MultipartFile file){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "ibUYQhnuwhq9FO844fTHxgwmlb5DdFRJg1GuYTd3";
        String secretKey = "1KEAmQzRej7PeVpC_4BYSXEhjS9_waEzqXDLqpGq";
        String bucket = "egobuy-yu";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            //http://q6kgs0l84.bkt.clouddn.com/
            return ResponseEntity.success("http://q6kgs0l84.bkt.clouddn.com/"+putRet.hash);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.fail(new CustomException(CustomExceptionType.USER_ERROR,"文件上传失败"));
    }
}
