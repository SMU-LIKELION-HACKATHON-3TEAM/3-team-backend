package com.grishare.service;

import com.grishare.domain.Post;
import com.grishare.dto.PostRequestDto;
import com.grishare.dto.PostReturnDto;

import java.util.List;

public interface PostService {

    Post save(Long nationId, PostRequestDto postRequestDto);

//    public PostReturnDto findById(Long postId);

    List<PostReturnDto> findByNationId(Long nationID);

    List<PostReturnDto> findByPostId(Long postId);

    public List<PostReturnDto> findAll();
    public PostReturnDto update(Long id, PostRequestDto postRequestDto);
    public void delete(Long id);
}
