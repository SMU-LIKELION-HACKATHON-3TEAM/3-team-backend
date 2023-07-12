package com.grishare.service;

import com.grishare.domain.Comment;
import com.grishare.domain.Post;
import com.grishare.domain.user.User;
import com.grishare.dto.CommentRequestDto;
import com.grishare.dto.CommentReturnDto;
import com.grishare.dto.PostRequestDto;
import com.grishare.dto.PostReturnDto;
import com.grishare.repository.CommentRepository;

import java.util.List;

public interface PostService {

    Post save(User user, Long nationId, PostRequestDto postRequestDto);


//    public PostReturnDto findById(Long postId);

    List<PostReturnDto> findByNationId(Long nationID);

    PostReturnDto findByPostId(Long postId);

    public List<PostReturnDto> findAll();
    public PostReturnDto update(Long id, PostRequestDto postRequestDto);
    public void delete(Long id);


}
