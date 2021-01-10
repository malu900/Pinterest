package com.fhict.service;

import com.fhict.dao.CollectionImageRepository;
import com.fhict.dao.CollectionRepository;
import com.fhict.dao.UserRepository;
import com.fhict.model.Collection;
import com.fhict.model.CollectionImage;
import com.fhict.model.User;
import com.fhict.service.payload.CollectionRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;
    private final CollectionImageRepository collectionImageRepository;

    public UserService(CollectionRepository collectionRepository, UserRepository userRepository,CollectionImageRepository collectionImageRepository) {
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
        this.collectionImageRepository = collectionImageRepository;
    }

    public List<Collection> getAllCollectionsFromUser(String username) {
        List<Collection> collections = collectionRepository.getCollectionsFromUser(username);
        return collections;
    }

    public Collection createCollection(UserPrincipal creator, String  collectionRequest, MultipartFile file) {
        Collection collection = new Collection(collectionRequest);
        Optional<User> user = userRepository.findById(creator.getId());
        User usermodel = user.get();
        collection.addUser(usermodel);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence ");
            }
            CollectionImage dbFile = new CollectionImage(fileName, file.getContentType(), file.getBytes());
//            collectionImageRepository.save(dbFile);
            collection.addCollectionImage(dbFile);

        }  catch (Exception e) {
            e.printStackTrace();
        }

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
