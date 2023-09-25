package com.ming.site.service.impl;

import com.ming.site.model.File;
import com.ming.site.repository.FileRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CrudService;
import com.ming.site.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl
        extends AbstractService<File, Long, FileRepository>
        implements FileService {
}
