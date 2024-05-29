package com.hana;

import com.hana.util.OCRUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Slf4j
class OCRTests {
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
