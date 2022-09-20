package com.sarac.sarac.global.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

@Slf4j
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


        if("profile".equals(type.toLowerCase(Locale.ROOT))){
            s3FileName = "profile/"+String.valueOf(id)+"."+extractExt(file.getOriginalFilename());

        }else if("review".equals(type)){
            s3FileName = "review/"+UUID.randomUUID() +"-"+String.valueOf(id)+"."+extractExt(file.getOriginalFilename());

        }

        amazonS3.putObject(bucket, s3FileName, file.getInputStream(), objMeta);
        return amazonS3.getUrl(bucket, s3FileName).toString();
    }


    public void fileDelete(String type,String fileName){

        String key = fileName.substring(fileName.lastIndexOf("/"));
        amazonS3.deleteObject(bucket+"/"+type,key.substring(1));

    }


    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
