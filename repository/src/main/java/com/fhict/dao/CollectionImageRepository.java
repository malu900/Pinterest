package com.fhict.dao;

import com.fhict.model.CollectionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionImageRepository extends JpaRepository<CollectionImage, Long> {
    Optional<CollectionImageRepository> findCollectionImageById(Long collectionId);
}
