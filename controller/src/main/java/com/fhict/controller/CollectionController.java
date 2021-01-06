package com.fhict.controller;

import com.fhict.model.Collection;
import com.fhict.model.User;
import com.fhict.security.config.CurrentUser;
import com.fhict.service.payload.CollectionRequest;
import com.fhict.service.UserPrincipal;
import com.fhict.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {
    private final UserService collectionService;

    public CollectionController(UserService collectionService) {
        this.collectionService = collectionService;
    }


    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createCollection(@CurrentUser UserPrincipal currentUser, @RequestBody CollectionRequest collectionRequest) {
        Collection collection = collectionService.createCollection(currentUser, collectionRequest);

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> removeCollection(@CurrentUser  UserPrincipal currentUser, @PathVariable("id") long id) {
        try {
            collectionService.removeCollection(currentUser, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
