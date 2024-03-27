package com.ming.site.api;

import com.ming.site.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @GetMapping("error")
    Result<String> error() {
        return Result.ok("error");
    }
}
