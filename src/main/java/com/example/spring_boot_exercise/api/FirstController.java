package com.example.spring_boot_exercise.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // REST를 위한 전용 Controller 기능 부여하는 Annotation
public class FirstController {
    @GetMapping("name") // Get Method 전용 Controller Annotation / "" 안에는 URL Mapping 정보를 입력
    public String name() {
        return "URL param으로 부터 이름을 받아서 출력해봅시다";
    }

    @GetMapping("name/{name}")
    // URL에서 {name}에 해당하는 Param값을 value = name에 받고 이를 (String) inputName으로 변환.
    public String getName(@PathVariable(value = "name") final String inputName) {
        return inputName;
    }

    @GetMapping("info")
    // URL을 통해 다수의 param값을 받아오기 (Query String 처리할 때 사용한다.)
    // value : query String에서의 key값 / defaultValue : query String 값이 없을 때의 기본 값
    // 예제 코드의 URI 예시 : [~/info?name=민준&age=25]
    public String getInfo(
            @RequestParam(value = "name", defaultValue = "홍길동") final String inputName,
            @RequestParam(value = "age", defaultValue = "0") final int inputAge) {
        return "이름 : " + inputName + "<br>나이 : " + inputAge;
    }
}
