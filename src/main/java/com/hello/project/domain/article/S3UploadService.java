package com.hello.project.domain.article;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3UploadService {

    @Value("${cloud.ncp.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    private String userId = "ncp-2889432-0";

    public String upload(MultipartFile multipartFile) {

        // upload file to bucket
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        ObjectMetadata objMeta = new ObjectMetadata();
        InputStream multipartInputStream = null;
        try {
            multipartInputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objMeta.setContentLength(multipartInputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        amazonS3.putObject(bucket, s3FileName, multipartInputStream, objMeta);

        // set object ACL
        try {
            amazonS3.setObjectAcl(bucket,s3FileName,CannedAccessControlList.PublicRead);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        }

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }
}
