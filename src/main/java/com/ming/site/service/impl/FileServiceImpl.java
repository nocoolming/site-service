package com.ming.site.service.impl;

import com.ming.site.config.UploadConfig;
import com.ming.site.model.File;
import com.ming.site.model.Product;
import com.ming.site.model.ProductImage;
import com.ming.site.repository.FileRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CrudService;
import com.ming.site.service.FileService;
import com.ming.site.service.ProductImageService;
import com.ming.site.service.storage.LocalUploadFile;
import com.ming.site.service.storage.UploadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class FileServiceImpl extends AbstractService<File, Long, FileRepository> implements FileService {
    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    @Autowired
    public FileServiceImpl(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;

        this.uploadFile = new LocalUploadFile(this.uploadConfig);
    }

    @Autowired
    ProductImageService productImageService;

    private UploadConfig uploadConfig;
    private UploadFile uploadFile;


    @Override
    @Transactional(propagation = Propagation.NESTED)
    public File store(MultipartFile file, String alt, long userId) throws Exception {
        String path = this.uploadFile.upload(file);

        File f = new File();
        f.setCreateAt(LocalDateTime.now());
        f.setUrl(path);
        f.setAlt(alt);
        f.setCreateUserId(userId);
        f.setPhysicalName(path);
        f.setLogicalName(file.getOriginalFilename());

        this.insert(f);

        return f;

    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public File storeProductImage(MultipartFile file, String alt, long userId, long productId) throws Exception {
        File returnFile = this.store(file, alt, userId);

        ProductImage productImage = new ProductImage();
        productImage.setProductId(productId);
        productImage.setAlt(alt);
        productImage.setCreateUserId(userId);
        productImage.setUrl(returnFile.getUrl());

        productImageService.insert(productImage);
        return returnFile;
    }

    @Override
    public void delete(String relativePath) throws IOException {
        String originPath = uploadConfig.getPath() + relativePath;

        log.debug("path: " + originPath);
        Path path = Paths.get(originPath);

        if (Files.exists(path)) {
            Files.delete(path);
        }
    }
}
