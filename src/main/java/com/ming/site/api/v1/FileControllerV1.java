package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.File;
import com.ming.site.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("site/v1/file")
public class FileControllerV1
        extends AbstractControllerV1<File, Long, FileService> {
    private static final Logger log = LoggerFactory.getLogger(FileControllerV1.class);


    @PostMapping("upload")
    public Result<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("alt") String alt,
            @RequestParam("userId") long userId,
            HttpServletRequest req)
            throws Exception {
        log.info(alt);


        String path = service.store(file, alt, userId);


        return Result.ok("file/v1/download" + path);
    }
}
