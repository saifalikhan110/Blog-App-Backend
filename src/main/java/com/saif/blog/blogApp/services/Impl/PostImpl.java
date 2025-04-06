package com.saif.blog.blogApp.services.Impl;

import com.saif.blog.blogApp.entities.Category;
import com.saif.blog.blogApp.entities.Post;
import com.saif.blog.blogApp.entities.User;
import com.saif.blog.blogApp.exceptions.ResourceNotFoundException;
import com.saif.blog.blogApp.payloads.PostDto;
import com.saif.blog.blogApp.payloads.PostResponse;
import com.saif.blog.blogApp.repositories.CategoryRepo;
import com.saif.blog.blogApp.repositories.PostRepo;
import com.saif.blog.blogApp.repositories.UserRepo;
import com.saif.blog.blogApp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user1=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user1);
        post.setCategory(category);
        Post savedpost = this.postRepo.save(post);

        return this.modelMapper.map(savedpost,PostDto.class);


    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {

        Post p=this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","post id",id));

        p.setTitle(postDto.getTitle());
        p.setContent(postDto.getContent());

        Post saved = this.postRepo.save(p);


        return this.modelMapper.map(saved,PostDto.class);
    }

    @Override
    public PostDto getPost(Integer id) {

        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "post id", id));



        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNum, Integer pageSize,String sortBy,String sortDir) {

        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc")){

            sort=Sort.by(sortBy).ascending();
        }
        else {
            sort=Sort.by(sortBy).descending();
        }
        Pageable p=PageRequest.of(pageNum,pageSize,sort);

        Page<Post> pagePost=this.postRepo.findAll(p);
        List<Post> postList=pagePost.getContent();



        List<PostDto> postDtos = postList.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNum(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public void deletePost(Integer id) {

        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "post id", id));

        this.postRepo.delete(post);

    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {


        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","category id",categoryId));

        List<Post> posts = this.postRepo.findByCategory(cat);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());


        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","user id",userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postList = posts.stream().map((user1) -> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());


        return postList;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {

        List<Post> searchedByTitle = this.postRepo.searchByTitle(keyword);
        List<PostDto> postDtos = searchedByTitle.stream().map((post) -> this.modelMapper.map(searchedByTitle, PostDto.class)).collect(Collectors.toList());


        return postDtos;
    }
}
