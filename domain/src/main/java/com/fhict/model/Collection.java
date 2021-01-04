package com.fhict.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "collection", orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    public String getCollectionname() {
        return collectionname;
    }

    public void setCollectionname(String collectionname) {
        this.collectionname = collectionname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addImage(Image image) {
        images.add(image);
        image.setCollection(this);
    }

    public void removeImage(Image image) {
        images.add(image);
        image.setCollection(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return id.equals(that.id) && collectionname.equals(that.collectionname) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collectionname, user);
    }
}
