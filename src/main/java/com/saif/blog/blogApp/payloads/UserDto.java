package com.saif.blog.blogApp.payloads;

import com.saif.blog.blogApp.entities.Comment;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private  int id;
    @NotEmpty
    private String name;
    @Email
    private String email;
    @NotEmpty
    @Size(min = 3,max = 20,message = "Password Must be equals to 3 or less than 21")
    @Pattern(regexp ="^(?=.*[A-Z])(?=.*\\d).+$",message = "Password Must Contain Uppercase Letter and number")
    private String password;
    @NotEmpty
    private String about;
    private List<Comment> commentList=new ArrayList<>();

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
