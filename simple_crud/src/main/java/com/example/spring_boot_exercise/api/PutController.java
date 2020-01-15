package com.example.spring_boot_exercise.api;

import com.example.spring_boot_exercise.model.User;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("put")
public class PutController {
    @PutMapping("") // PUT Method 전용 Controller Annatation
    public int putUser(@RequestBody final User user) {
        return user.getAge();
    }
}