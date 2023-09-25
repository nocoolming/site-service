package com.ming.site.service;

import com.ming.site.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService extends CrudService<File, Long> {

    String store(MultipartFile file, String alt, long userId) throws Exception;


    void delete(String relativePath) throws IOException;
}
