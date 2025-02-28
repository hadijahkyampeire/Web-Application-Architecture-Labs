package waa.labs.waalabs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class TestExceptionController {

    @GetMapping
    public void testException() {
        throw new RuntimeException("Test Exceptions Logger");
    }
}
