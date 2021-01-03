package com.fhict.service;

import com.fhict.dao.UserRepository;
import com.fhict.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    private final UserRepository usersRepository;

    @PostConstruct
    public void InitUser(){
        usersRepository.saveAll(Stream.of(new User("malu", "password"),new User("jane", "password")).collect(Collectors.toList()));
    }

    public List<User> getUsers() {
        return usersRepository.findAll();
    }
    public UserService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
