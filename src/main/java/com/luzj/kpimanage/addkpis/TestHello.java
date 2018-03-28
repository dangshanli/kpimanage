package com.luzj.kpimanage.addkpis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzj
 * @description:
 * @date 2018/3/12
 */
@RestController
public class TestHello {
    @RequestMapping("/hello")
    public String printHello(){
        return "hello";
    }
}
