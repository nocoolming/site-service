package com.ming.site.api.v1;

import com.ming.site.config.UploadConfig;
import com.ming.site.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("site/v1/download")
public class DownloadControllerV1 {
    private static final Logger log = LoggerFactory.getLogger(DownloadControllerV1.class);

    @Autowired
    FileService fileService;
    @Autowired
    UploadConfig uploadConfig;

    @GetMapping(value="/**")
    public ResponseEntity downloadFileFromLocal(HttpServletRequest req) {
        String originPath = uploadConfig.getPath()
                +req.getRequestURI()
                .replace("/site/v1/download", "");

        log.error(uploadConfig.getPath());
        log.error(req.getRequestURI());
        log.error("originPath:");
        log.error(originPath);

        Path path = Paths.get(originPath);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream."))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

//    @GetMapping(
//            value = "/download/**",
//            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
//    )
//    public @ResponseBody byte[] getFile(HttpServletRequest req) throws IOException {
//        String path = uploadConfig.getPath()
//                + req.getRequestURI()
//                .replace("file/v1/download", "");
//
//        if(this.exists(path)) {
//            byte[] bytes = this.getBytes(path);
//
//            return bytes;
//        }
//
//        return null;
//    }

}
