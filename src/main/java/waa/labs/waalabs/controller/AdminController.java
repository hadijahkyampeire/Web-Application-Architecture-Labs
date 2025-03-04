package waa.labs.waalabs.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}
