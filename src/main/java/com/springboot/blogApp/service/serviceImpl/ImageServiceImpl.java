package com.springboot.blogApp.service.serviceImpl;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.springboot.blogApp.service.FileService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class ImageServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file)
    {
        //file Name
        String name = file.getOriginalFilename();

        //create random id
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));

        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdir();
        }
        try {
            path = path+File.separator+fileName;
            Files.copy(file.getInputStream(), Paths.get(path));
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "something went wrong";
    }



    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;

    }
}


