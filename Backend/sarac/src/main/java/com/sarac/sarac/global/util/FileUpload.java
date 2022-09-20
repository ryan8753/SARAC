package com.sarac.sarac.global.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FileUpload {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;


    public String fileUpload(MultipartFile file, Long id, String type) throws IOException {

        String s3FileName = null;

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(file.getInputStream().available());
        String path=null;

        if("profile".equals(type.toLowerCase(Locale.ROOT))){
            s3FileName = String.valueOf(id)+"."+extractExt(file.getOriginalFilename());
            path="/profile";
        }else if("review".equals(type)){
            s3FileName = UUID.randomUUID() +"-"+String.valueOf(id)+"."+extractExt(file.getOriginalFilename());
            path="/review";
        }

        amazonS3.putObject(bucket+path, s3FileName, file.getInputStream(), objMeta);

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    public void fileDelete(String type,String fileName){

        String s3FileName = fileName;
        String path=null;

        if("profile".equals(type.toLowerCase(Locale.ROOT))){
            path="/profile";
        }else if("review".equals(type)){
            path="/review";
        }
        amazonS3.deleteObject(bucket+path, s3FileName);

    }


    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
