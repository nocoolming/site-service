package com.ming.site.service;

import com.ming.site.model.File;
import com.ming.site.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService extends CrudService<File, Long> {

    File store(MultipartFile file, String alt, long userId) throws Exception;

    ProductImage storeProductImage(MultipartFile file, String alt, long userId, long productId) throws Exception;


    void delete(String relativePath) throws IOException;
}
