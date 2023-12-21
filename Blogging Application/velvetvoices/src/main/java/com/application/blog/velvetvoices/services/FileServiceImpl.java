package com.application.blog.velvetvoices.services;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


    private Logger logger = org.slf4j.LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        logger.info("File name : {}",file.getOriginalFilename());

        //Filename
        String fileName = file.getOriginalFilename();

        //Fullpath
        String filePath = path + fileName;


        //Create folder if not created
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }

        //File copy

        Files.copy(file.getInputStream(), Paths.get(filePath));

        logger.info("File uploaded successfully");
        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws IOException {
        logger.info("File name : {}",fileName);
        String fullPath = path + fileName;

        logger.info("Full Path : {}",fullPath);
        return Files.newInputStream(Paths.get(fullPath));

    }
}
