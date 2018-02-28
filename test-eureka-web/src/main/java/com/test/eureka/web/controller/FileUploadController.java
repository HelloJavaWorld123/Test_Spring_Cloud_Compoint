package com.test.eureka.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/27  17:32
 * Version: V1.0
 * Description: 文件上传
 * ======================
 */
@RestController
public class FileUploadController {

    @Value("${File.upload.maxSize}")
    private String  maxFileSize;

    @RequestMapping("/fileUpload")
    public ResponseEntity fileUpload(@RequestBody MultipartFile fileName, HttpServletRequest request){

        if(null == fileName ){
            return ResponseEntity.ok("文件不能为空");
        }

        String originalFilename = fileName.getOriginalFilename();

        long size = fileName.getSize();

        if(size>Integer.valueOf(maxFileSize)){
            return ResponseEntity.ok("文件大小超过限制");
        }

        String time = System.currentTimeMillis() + "";
        String newFileName = time +"-" + originalFilename ;

        File file = new File("D:\\FileUpload");

        try {
            FileOutputStream fos = new FileOutputStream(newFileName);

            fos.write(fileName.getBytes());
            fos.flush();

            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("文件输出异常");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("文件写出异常");
        }
        return ResponseEntity.ok("文件成功输出");
    }



}
