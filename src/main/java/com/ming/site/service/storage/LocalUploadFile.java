package com.ming.site.service.storage;

import com.ming.site.config.UploadConfig;
import com.ming.site.service.storage.compression.BasicCompress;
import com.ming.site.service.storage.compression.Compress;
import com.ming.site.service.storage.path.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class LocalUploadFile extends UploadFile {
    private static final Logger log = LoggerFactory.getLogger(LocalUploadFile.class);

    public LocalUploadFile(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
        this.pathProcess = new LocalPathProcess(this.uploadConfig.getPath());
        this.compress = new BasicCompress(this.uploadConfig);
    }

    UploadConfig uploadConfig;

    private PathProcess pathProcess;
    private Compress compress;

    @Override
    public String upload(MultipartFile file)
            throws Exception {
        String filePath = null;
        try {
            filePath = pathProcess.processPath(file.getOriginalFilename());
            String path = this.compress.compress(file.getInputStream(), filePath);

            return path;
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
