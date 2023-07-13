package com.grishare.service;

import com.grishare.domain.Comment;
import com.grishare.domain.Post;
import com.grishare.domain.user.User;
import com.grishare.dto.*;
import com.grishare.repository.CommentRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

//    public PostReturnDto findById(Long postId);

    PostReturnDto save(User user, Long nationId, PostRequestDto postRequestDto, List<MultipartFile> imageFiles);

    List<PostReturnDto> findByNationId(Long nationID);

    PostDetailReturnDto findByPostId(Long postId);

    public List<PostReturnDto> findAll();
    public PostReturnDto update(Long id, PostRequestDto postRequestDto);
    public void delete(Long id);


}
