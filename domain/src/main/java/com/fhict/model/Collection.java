package com.fhict.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "collections")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String collectionname;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_collections",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "collection_id") })
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "collection",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<Image> images = new HashSet<>();

    public Collection(String collectionname){
        this.collectionname = collectionname;
    }

    public Collection() {

    }

    public String getCollectionname() {
        return collectionname;
    }

    public void setCollectionname(String collectionname) {
        this.collectionname = collectionname;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUser(User user) {
        this.user = user;
        user.addCollection(this);
    }
    public void removeUser(User user) {
        this.user = null;
        user.removeCollection(this);
    }
    public void addUsers(User user) {
        this.users.add(user);
        user.addCollection(this);
    }
    public void removeUsers(User user) {
        this.users = null;
        user.removeCollection(this);
    }
    public void addImage(Image image) {
        images.add(image);
        image.setCollection(this);
    }

    public void removeImage(Image image) {
        images.add(image);
        image.setCollection(this);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    //    public Set<User> getUsers() {
//        return users;
//    }
//    public void setUser(User user) {
//        user.addCollection(this);
//        this.users.add(user);
//    }
//    public void removeUser(User user) {
//        this.users.remove(user);
//    }
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
