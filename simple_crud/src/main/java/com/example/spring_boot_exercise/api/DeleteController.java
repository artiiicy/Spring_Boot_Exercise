package com.example.spring_boot_exercise.api;

import com.example.spring_boot_exercise.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("delete")
public class DeleteController {
    @DeleteMapping("")  // Delete 전용 Method Controller Annotation
    public String deleteUser(@RequestBody final User user) {
        return "Delete Success";
    }
}
