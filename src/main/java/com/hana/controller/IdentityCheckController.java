package com.hana.controller;

import com.hana.util.OCRUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/id")
public class IdentityCheckController {
    @PostMapping("/ocr")
    public String ocr(@RequestParam("image") MultipartFile image) {
        log.info("==========checking image========="+image.getOriginalFilename());
        if(image==null){
            log.info("image is null check your code");
            return "image is null";
        }
        try{
            String imgname = image.getOriginalFilename();
            String imgpath = "C:/project/imgs/";
            File imgFile = new File(imgpath+imgname);
            image.transferTo(imgFile);

            JSONObject jsonObject = OCRUtil.getResult(imgpath, imgname);
            log.info(jsonObject.toJSONString());

            Map<String,String> map = OCRUtil.getData(jsonObject);
            map.values().forEach(txt->{log.info(txt);});

            return jsonObject.toJSONString();

        }catch(IOException e){
            log.error("File transfer failed", e);
            return "File transfer failed";
        }
    }
}
