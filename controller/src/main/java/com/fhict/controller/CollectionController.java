package com.fhict.controller;

import antlr.StringUtils;
import com.fhict.controller.exception.ResourceNotFoundException;
import com.fhict.model.Collection;
import com.fhict.model.CollectionImage;
import com.fhict.model.User;
import com.fhict.security.config.CurrentUser;
import com.fhict.service.CollectionService;
import com.fhict.service.payload.CollectionRequest;
import com.fhict.service.UserPrincipal;
import com.fhict.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {
    private final UserService userService;
    private final CollectionService collectionService;

    public CollectionController(UserService userService, CollectionService collectionService) {
        this.userService = userService;
        this.collectionService = collectionService;
    }


    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createCollection(@CurrentUser UserPrincipal currentUser, @RequestParam("collectionName") String collectionRequest, @RequestParam("file") MultipartFile file) {
        Collection collection = null;
        try{
            collection = userService.createCollection(currentUser, collectionRequest, file);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> removeCollection(@CurrentUser  UserPrincipal currentUser, @PathVariable("id") long id) {
        try {
            userService.removeCollection(currentUser, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<HttpStatus> removeCollection(@CurrentUser  UserPrincipal currentUser, @PathVariable("id") long id) {
//        try {
//            collectionService.removeCollection(currentUser, id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    private static final Logger logger = Logger.getLogger(PinterestApplication.class.getName());
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file) throws Exception {
//        if (file == null) {
//            throw new RuntimeException("You must select the a file for uploading");
//        }
//        InputStream inputStream = file.getInputStream();
//        String originalName = file.getOriginalFilename();
//        String name = file.getName();
//        String contentType = file.getContentType();
//        long size = file.getSize();
//        logger.info("inputStream: " + inputStream);
//        logger.info("originalName: " + originalName);
//        logger.info("name: " + name);
//        logger.info("contentType: " + contentType);
//        logger.info("size: " + size);
//        // Do processing with uploaded file data in Service layer
//        return new ResponseEntity<String>(originalName, HttpStatus.OK);
//    }

}
