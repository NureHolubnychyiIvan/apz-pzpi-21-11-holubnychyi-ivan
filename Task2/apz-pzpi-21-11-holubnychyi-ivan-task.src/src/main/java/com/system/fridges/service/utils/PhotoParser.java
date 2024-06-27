package com.system.fridges.service.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class PhotoParser {

    private static final String UPLOAD_PATH = "/fridges/img";

    private final MultipartFile file;

    private String absolutePath;

    public PhotoParser(MultipartFile multipartFile) {
        this.file = multipartFile;
    }

    public void savePhoto() {
        if (file == null || file.isEmpty() || file.getOriginalFilename() == null) return;
        try {
            String folderPath = System.getProperty(UPLOAD_PATH);
            File destination = new File(folderPath, Objects.requireNonNull(file.getOriginalFilename()));
            FileCopyUtils.copy(file.getBytes(), destination);
            absolutePath = destination.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAbsolutePath() {
        if (absolutePath != null) {
            return absolutePath;
        }
       return "";
    }

    public static byte[] pullPhoto(String photoPath){
        if (photoPath != null) {
            try {
                Path path = Paths.get(photoPath);
                return Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
