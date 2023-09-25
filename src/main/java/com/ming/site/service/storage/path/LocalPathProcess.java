package com.ming.site.service.storage.path;


import com.ming.site.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDateTime;

public class LocalPathProcess implements PathProcess {
    private final Logger log = LoggerFactory.getLogger(LocalPathProcess.class);

    public LocalPathProcess(){

    }

    public LocalPathProcess(String bashDirPath){
        this.bashDirPath = bashDirPath;
    }

    private String bashDirPath;

    @Override
    public String processPath(String filename)
            throws Exception {
        String ext = ExtensionUtil.getExt(filename);

        StringBuffer path = new StringBuffer();
        LocalDateTime now =LocalDateTime.now();

//        path.append(this.bashDirPath);
        path.append("/");
        path.append(now.getYear());
        path.append("/");
        path.append(now.getMonth());
        path.append("/");
        path.append(now.getDayOfMonth());
        path.append("/");
//        path.append(now.getHour());
//        path.append("/");
//        path.append(now.getMinute());
//        path.append("/");
//        path.append(now.getSecond());
//        path.append("/");

        String dirPath = this.bashDirPath + path.toString();
        File file = new File(dirPath);

        if(!file.exists()){
            file.mkdirs();
        }

        path.append(SnowflakeUtil.nextId());
        path.append(ext);

        return path.toString();
    }
}
