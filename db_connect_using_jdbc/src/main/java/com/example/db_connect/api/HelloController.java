package com.example.db_connect.api;


import com.example.db_connect.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("")
    public String get(ModelMap modelMap) throws Exception {
        //Map<String, String> test = Collections.singletonMap("name", "Spring Boot 연습중...");
        //Map<String, Object> result = Collections.singletonMap("test", test);

        modelMap.put("result", helloService.getTest());

        return "/hello/get";
    }
}
