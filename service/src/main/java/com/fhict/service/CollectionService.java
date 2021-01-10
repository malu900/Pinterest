package com.fhict.service;

import com.fhict.dao.CollectionImageRepository;
import com.fhict.dao.CollectionRepository;
import com.fhict.model.CollectionImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class CollectionService {
//    @Autowired
//    private CollectionImageRepository collectionImageRepository;
//    @Autowired
//    private CollectionRepository collectionRepository;
//
//    public CollectionImage store(MultipartFile file){
//
//    }


//    public CollectionImage store(MultipartFile file) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        CollectionImage FileDB = new CollectionImage(fileName, file.getContentType(), file.getBytes());
//
//        return collectionImageRepository.save(FileDB);
//    }


}
