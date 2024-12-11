package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.Impl;

import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.DTO.PostDTO;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Entity.PostEntity;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Entity.UserEntity;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Exception.ResourceNotFoundException;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Repository.PostRepository;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.PostServices;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@ToString
public class PostServiceImpl implements PostServices {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);

        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {

        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("user {}", user);

        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with this  id "+postId));

        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity olderPost = postRepository
                .findById(postId)
                .orElseThrow(() ->new ResourceNotFoundException("Post not found with this  id "+postId));

        inputPost.setId(postId);
        modelMapper.map(inputPost, olderPost);

        return modelMapper.map(postRepository.save(olderPost), PostDTO.class);
    }
}
