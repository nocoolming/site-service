package com.ming.site.service.storage.path;

public class ExtensionUtil {
    public final static String EXTENSIONS = ".jpg|.png|.gif|.jpeg|.webp";

    public static String getExt(String filename) throws Exception {
        if (filename == null || filename.isEmpty()) {
            throw new Exception("Not found file");
        }

        int i = filename.lastIndexOf(".");
        String ext = filename.substring(i);

        if (ext == null || ext.isEmpty()) {
            throw new Exception("Not found extension.");
        }

        if (EXTENSIONS.indexOf(ext.toLowerCase()) == -1) {
            throw new Exception("Not support extension.");
        }

        ext = ext.toLowerCase();
        return ext;
    }
}
