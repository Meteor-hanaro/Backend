package com.hana.controller;

import com.hana.app.data.entity.IntegratedVip;
import com.hana.app.service.user.UsersService;
import com.hana.util.OCRUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/id")
@RequiredArgsConstructor
public class IdentityCheckController {
    @Value("${apikey.ocrkey}")
    private String ocrkey;
    @Value("${apikey.ocrurl}")
    private String ocrurl;
    private final UsersService usersService;
    @PostMapping("/ocr")
    public String ocr(@RequestParam("image") MultipartFile image, @RequestParam("vipId") Long vipId) {
        log.info("==========checking image========="+image.getOriginalFilename());
        log.info("==========checking vipid========="+vipId);
        IntegratedVip vipInfo = usersService.findByVipID(vipId);
        log.info(vipInfo.toString());

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
            Set<String> ls = map.keySet();
            Iterator iter = ls.iterator();
            while(iter.hasNext()){
                String key = (String)iter.next();
                log.info(key+"는"+map.get(key));
            }
            if(vipInfo.getName().equals(map.get("vipname"))){
                return "인증에 성공했습니다.";
            }
            return "인증에 실패했습니다. 다시 시도하세요.";

        }catch(IOException e){
            log.error("File transfer failed", e);
            return "File transfer failed";
        }
    }
}
