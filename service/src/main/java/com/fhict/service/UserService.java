package com.fhict.service;

import com.fhict.dao.CollectionRepository;
import com.fhict.dao.UserRepository;
import com.fhict.model.Collection;
import com.fhict.model.User;
import com.fhict.service.payload.CollectionRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;

    public UserService(CollectionRepository collectionRepository, UserRepository userRepository) {
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
    }

    public List<Collection> getAllCollectionsFromUser(String username) {
        List<Collection> collections = collectionRepository.getCollectionsFromUser(username);
        return collections;
    }

    public Collection createCollection(UserPrincipal creator, CollectionRequest collectionRequest) {
        Collection collection = new Collection(collectionRequest.getCollectionName());
        Optional<User> user = userRepository.findById(creator.getId());
        User usermodel = user.get();
        collection.addUser(usermodel);

        return collectionRepository.save(collection);
    }
//    @Transactional
    public void removeCollection(UserPrincipal creator, Long id) {
        try {
            Optional<Collection> collection = collectionRepository.findById(id);
            Collection collection1 = collection.get();
            Optional<User> user = userRepository.findById(creator.getId());
            User usermodel = user.get();
            collection1.removeUsers(usermodel);
            usermodel.removeCollection(collection1);
            collectionRepository.deleteById(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
