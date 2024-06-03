package com.hana.controller;

import com.hana.util.OCRUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/id")
public class IdentityCheckController {
    @Value("${apikey.ocrkey}")
    private String ocrkey;
    @Value("${apikey.ocrurl}")
    private String ocrurl;

    @PostMapping("/ocr")
    public String ocr(@RequestParam("image") MultipartFile image) {
        log.info("==========checking image========="+image.getOriginalFilename());
//        Authentication authentication = jwtTokenProvider.getAuthentication(token);
//        autthentication.getName();
        if(image==null){
            log.info("image is null check your code");
            return "image is null";
        }
        try{
            String imgname = image.getOriginalFilename();
            String imgpath = "C:/project/imgs/";
            File imgFile = new File(imgpath+imgname);
            image.transferTo(imgFile);

            JSONObject jsonObject = OCRUtil.getResult(ocrkey, ocrurl, imgpath, imgname);
            log.info(jsonObject.toJSONString());

            Map<String,String> map = OCRUtil.getData(jsonObject);
            map.values().forEach(txt->{log.info(txt);});


            return jsonObject.toJSONString();

        }catch(IOException e){
            log.error("File transfer failed", e);
            return "File transfer failed";
        }
    }
//    @PostMapping("/combinePdf")
//    public String combinePdf() {
//        log.info("==========checking signiture=========");
//        return "Good";
//    }
}
