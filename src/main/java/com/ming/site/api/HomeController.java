package com.ming.site.api;

import com.ming.site.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    DataSource dataSource;

    @GetMapping("/")
    String home() {
        return "Site service v0.0.1";
    }


    @GetMapping("site")
    Result<String> site() {
        return Result.ok("Site service");
    }

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getRobotsTxt() {
        return "User-agent: *\n" + "Disallow: /\n";
    }

//    @GetMapping("site/dataSource")
    Result<DataSource> dataSource() {
        return Result.ok(dataSource);
    }
}
