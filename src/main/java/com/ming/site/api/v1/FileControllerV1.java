package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.common.Result;
import com.ming.site.model.File;
import com.ming.site.model.ProductImage;
import com.ming.site.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("site/v1/file")
public class FileControllerV1
        extends AbstractControllerV1<File, Long, FileService> {
    private static final Logger log = LoggerFactory.getLogger(FileControllerV1.class);



    @PostMapping("upload")
    public Result<File> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("alt") String alt,
            HttpServletRequest req)
            throws Exception {
        log.info(alt);


//        String name = req.get

        File returnFile = service.store(file, alt);


        return Result.ok(returnFile);
    }

    @PostMapping("uploadProductImage")
    public Result<ProductImage> uploadProductImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("alt") String alt,
            @RequestParam("userId") long userId,
            @RequestParam("productId") long productId,
            HttpServletRequest req)
            throws Exception {
        log.info(alt);


        ProductImage returnFile= service.storeProductImage(file, alt,  userId, productId);


        return Result.ok(returnFile);
    }
}
