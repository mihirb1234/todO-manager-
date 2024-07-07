package com.lcwd.todo.todo_manager.controllers;


import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactoryFriend;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

@RestController

@RequestMapping("/file")
public class FileController {



    Logger logger= LoggerFactory.getLogger(FileController.class);
    @PostMapping("/single")
    public String uploadSingle(@RequestParam("image")MultipartFile file){
        logger.info("Name: {} ",file.getName());
        logger.info("ContentType : {} ",file.getContentType());
        logger.info("OriginalFileName : {}",file.getOriginalFilename());
        logger.info("File Size : {}",file.getSize());

        return "File Uploaded";
    }


    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files")MultipartFile[] files){
        Arrays.stream(files).forEach(file->{
            logger.info("file Name : {} ",file.getOriginalFilename());
            logger.info("file type : {} ",file.getContentType());
            System.out.println("-------------------------------------------------------");
        });
        return "Handles multiple files";
    }

    //serve image files in api response
    @GetMapping("/serve-images")
    public void serveImages(HttpServletResponse response){

        try{
            //copy the image in output file stream
            FileInputStream fileInputStream=new FileInputStream("images/martin odegaard.png");
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            StreamUtils.copy(fileInputStream,response.getOutputStream());

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
