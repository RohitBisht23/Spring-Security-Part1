package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service;


import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.PostDTO;

import java.util.List;


public interface PostServices {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO postDTO);

    public PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
