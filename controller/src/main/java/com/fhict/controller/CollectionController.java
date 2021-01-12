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
import java.util.ArrayList;
import java.util.List;

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
    @CrossOrigin(origins = "http://localhost:3000")
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
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCollectionsFromUser(@CurrentUser UserPrincipal currentUser) {
        List<Collection> collection = new ArrayList<>();
        try{
            collection.addAll(userService.getAllCollectionsFromUser(currentUser));
//            collection = userService.getAllCollectionsFromUser(currentUser);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @RequestMapping(value = "/{collectionId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCollectionByUserIdAndCollectionId(@CurrentUser UserPrincipal currentUser, @PathVariable  Long collectionId) {
        Collection collection = null;
        try{
            collection = userService.getCollectionByIdAndUserId(currentUser, collectionId);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

//    @GetMapping("/downloadFile/{fileName:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
//        // Load file as Resource
//        Resource resource = fileStorageService.loadFileAsResource(fileName);
//
//        // Try to determine file's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
}
