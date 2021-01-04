package com.fhict.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String imageName;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @OneToMany(mappedBy = "image", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "image", orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public void addLike(Like like) {
        likes.add(like);
        like.setImage(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setImage(this);
    }

    public void removeLike(Like like) {
        likes.remove(like);
        like.setImage(null);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setImage(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id.equals(image.id) && imageName.equals(image.imageName) && collection.equals(image.collection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageName, collection);
    }
}
