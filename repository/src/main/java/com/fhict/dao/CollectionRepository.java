package com.fhict.dao;


import com.fhict.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Optional<Collection> findCollectionById(Long collectionId);
    List<Collection> findCollectionByIdIn(List<Long> collectionId);
//    @Query("SELECT u.collections FROM User u where u.username = :userName")
    @Query("SELECT c FROM Collection c where c.user.id = :id")
    List<Collection> getCollectionsFromUser(Long id);
    @Query("select c from Collection c where c.user.id = :id and c.id = :collectionId")
    Optional<Collection> findCollectionByIdAndUserId(Long id, Long collectionId);
}
