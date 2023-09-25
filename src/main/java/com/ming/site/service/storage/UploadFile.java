package com.ming.site.service.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public abstract class UploadFile {
    private static final Logger log = LoggerFactory.getLogger(UploadFile.class);


    public abstract String upload(MultipartFile file) throws  Exception;


}
