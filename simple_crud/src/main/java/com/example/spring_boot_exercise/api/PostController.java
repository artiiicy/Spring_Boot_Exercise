package com.example.spring_boot_exercise.api;

import com.example.spring_boot_exercise.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post") // Post : 리소스 생성하는 요청
public class PostController {
    @PostMapping("")    // Post Method 전용 Controller Annotation
    // 전송한 객체와 Controller의 객체 타입이 같아야 한다! (이 예제코드의 같은 경우 : User)
    public String postUser(@RequestBody final User user) {
        return user.getName();
    }
}