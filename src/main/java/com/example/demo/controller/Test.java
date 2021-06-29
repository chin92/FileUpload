package com.example.demo.controller;

import com.example.demo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/files")
public class Test {

    @Autowired
    FileService fileService;

    // The required method

    @RequestMapping(value="/uploadstream",method=RequestMethod.POST)
    public void saveFiles(HttpServletRequest request) throws IOException, FileUploadException {
        ServletFileUpload upload = new ServletFileUpload();
        FileItemIterator iterStream = upload.getItemIterator(request);
        while (iterStream.hasNext()) {
            FileItemStream item = iterStream.next();
            String name = item.getFieldName();
            InputStream stream = item.openStream();
            if (!item.isFormField()) {
                // Save the file
            } else {
                String formFieldValue = Streams.asString(stream);
            }
        }
    }

//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public String handleUpload(HttpServletRequest request) {
//        System.out.println(System.getProperty("java.io.tmpdir"));
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//        // Create a factory for disk-based file items
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//        factory.setSizeThreshold(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD);
//        factory.setFileCleaningTracker(null);
//        // Configure a repository (to ensure a secure temp location is used)
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        try {
//            // Parse the request
//            List<FileItem> items = upload.parseRequest(request);
//            // Process the uploaded items
//            Iterator<FileItem> iter = items.iterator();
//            while (iter.hasNext()) {
//                FileItem item = iter.next();
//
//                if (!item.isFormField()) {
//                    try (InputStream uploadedStream = item.getInputStream();
//                         OutputStream out = new FileOutputStream("file.mov");) {
//                        IOUtils.copy(uploadedStream, out);
//                        out.close();
//                    }
//                }
//            }
//            // Parse the request with Streaming API
//            upload = new ServletFileUpload();
//            FileItemIterator iterStream = upload.getItemIterator(request);
//            while (iterStream.hasNext()) {
//                FileItemStream item = iterStream.next();
//                String name = item.getFieldName();
//                InputStream stream = item.openStream();
//                if (!item.isFormField()) {
//                    //Process the InputStream
//                } else {
//                    //process form fields
//                    String formFieldValue = Streams.asString(stream);
//                }
//            }
//            return "success!";
//        } catch (IOException | FileUploadException ex) {
//            return "failed: " + ex.getMessage();
//        }
//    }
//
//    @PostMapping("/uploadfiles")
//    public ResponseEntity<?> handleFileUpload(
//            @RequestParam("uploaded-file") List<MultipartFile> uploadedFiles)
//            throws IOException {
//        log.debug("Uploaded files size : {}", uploadedFiles.size());
//        fileService.copyFile(uploadedFiles);
//        return ResponseEntity.ok().build();
//    }

}
