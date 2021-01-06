package com.fhict.controller;

import com.fhict.model.Collection;
import com.fhict.security.config.CurrentUser;
import com.fhict.service.payload.CollectionRequest;
import com.fhict.service.UserPrincipal;
import com.fhict.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
