package com.ming.site.service.storage.compression;

import java.io.IOException;
import java.io.InputStream;

public interface Compress {

    String compress(InputStream input, String compressedPath) throws Exception;
}
