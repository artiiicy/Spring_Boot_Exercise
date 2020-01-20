package com.example.file_upload.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

@Slf4j
@RestController
@RequestMapping("/attachments")
public class AttachController {
    String zipFileDir  = null;
    String zipFileName = null;
    String unzipDir    = null;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> uploadAttachment(@RequestPart MultipartFile sourceFile) throws IOException {

        String sourceFileName = sourceFile.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();  //*.getExtension() : 확장자 반환

        File destinationFile;
        String destinationFileName;
        do {
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File("C:/attachments/" + destinationFileName);
        } while (destinationFile.exists());
        destinationFile.getParentFile().mkdirs();   // *.mkdirs() : 상위 폴더가 존재하지 않으면 상위 폴더마저도 생성해준다
        sourceFile.transferTo(destinationFile);

        UploadAttachmentResponse response = new UploadAttachmentResponse();
        response.setFileName(sourceFile.getOriginalFilename());
        response.setFileSize(sourceFile.getSize());
        response.setFileContentType(sourceFile.getContentType());

        // zip 형식인지 아닌지 판별하는 조건문
        if(sourceFile.getContentType().equals("application/x-zip-compressed") || sourceFile.getContentType().equals("application/zip")) {
            // zip 형식이라면 압축을 풀고 upload
            log.info("it is " + sourceFile.getContentType() + " file");

            // 출처 : http://tutorials.jenkov.com/java-zip/zipfile.html
            ZipFile zipFile = new ZipFile("C:/attachments/" + destinationFileName);

            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                log.info(entry.getName() + " is unzipping");

                if (entry.isDirectory()) {
                    System.out.print("dir  : " + entry.getName());
                    String destPath = "C:/attachments/" + entry.getName();
                    System.out.println(" => " + destPath);

                    //todo check destPath for Zip Slip problem - see further down this page.

                    File file = new File(destPath);
                    file.mkdirs();
                } else {
                    String destPath = "C:/attachments/" + entry.getName();

                    //todo check destPath for Zip Slip problem - see further down this page.

                    try (InputStream inputStream = zipFile.getInputStream(entry);
                         FileOutputStream outputStream = new FileOutputStream(destPath);
                    ) {
                        int data = inputStream.read();
                        while (data != -1) {
                            outputStream.write(data);
                            data = inputStream.read();
                        }
                    }
                }
            }
        }
        else
        {
            // zip 형식이 아니라면 바로 upload
            log.info("it is " + sourceFile.getContentType() + " file");
        }

        response.setAttachmentUrl("http://localhost:8080/attachments/" + destinationFileName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @NoArgsConstructor
    @Data
    private static class UploadAttachmentResponse {

        private String fileName;

        private long fileSize;

        private String fileContentType;

        private String attachmentUrl;
    }
}