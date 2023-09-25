package com.ming.site.service.storage.compression;

import com.ming.site.config.UploadConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

public class BasicCompress implements Compress {
    private static final Logger log = LoggerFactory.getLogger(BasicCompress.class);

    public BasicCompress(UploadConfig uploadConfig){
        this.uploadConfig = uploadConfig;
    }

    private UploadConfig uploadConfig;

    @Override
    public String compress(InputStream input, String compressedPath) throws IOException {
        OutputStream outputStream = null;
        ImageOutputStream ios = null;
        ImageWriter writer = null;
        try {
            BufferedImage image = ImageIO.read(input);

            File compressedImageFile = new File(compressedPath);

            String filePath = uploadConfig.getPath() + compressedPath;
//            String ext = ExtensionUtil.getExt(compressedPath).replace(".","");
            outputStream = new FileOutputStream(filePath);
//            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(ext);
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

            writer = (ImageWriter) writers.next();

            ios = ImageIO.createImageOutputStream(outputStream);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.55f);
            writer.write(null, new IIOImage(image, null, null), param);

        } catch (Exception e) {
            log.error(e.getMessage());

            throw e;
        } finally {
            if(outputStream != null){
                outputStream.close();
            }
            if(ios != null) {
                ios.close();
            }
            if(writer != null) {
                writer.dispose();
            }
        }
        return compressedPath;
    }
}
