package com.ming.site.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfig {
    private static final Logger log = LoggerFactory.getLogger(UploadConfig.class);

    // 存储根目录
    @Value("${upload.path}")
    private String path;

    @Value("${upload.size}")
    private int sze;

    @Value("${upload.token}")
    private String token;

    /// 文件类型，例：jpg,png,gif,jpeg
    @Value("${upload.types}")
    private String types;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSze() {
        return sze;
    }

    public void setSze(int sze) {
        this.sze = sze;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
