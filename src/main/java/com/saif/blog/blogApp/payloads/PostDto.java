package com.saif.blog.blogApp.payloads;

import com.saif.blog.blogApp.entities.Comment;

import java.util.HashSet;
import java.util.Set;

public class PostDto {


    private Integer postId;
    String title;
    String content;

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    private Set<Comment> commentSet=new HashSet<>();

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
