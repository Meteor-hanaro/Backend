package com.hana;


import com.hana.util.OCRUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import com.hana.config.JasyptConfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootTest
@Slf4j
class MeteorBackendApplicationTests {

    String dir = "C:/project/imgs/";
    @Test
    void contextLoads() {
        String imgname = "license.jpg";
        JSONObject jsonObject = (JSONObject) OCRUtil.getResult(dir,imgname);
        log.info(jsonObject.toJSONString());
        Map<String,String> map = OCRUtil.getData(jsonObject);
        map.values().forEach(txt->{log.info(txt);});
    }
}
