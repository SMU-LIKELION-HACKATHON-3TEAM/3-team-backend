package com.grishare.service;

import com.grishare.domain.Post;
import com.grishare.dto.PostRequestDto;
import com.grishare.dto.PostReturnDto;

import java.util.List;

public interface PostService {
    public Post save(PostRequestDto productRequestDto);
    public PostReturnDto findById(Long id);
    public List<PostReturnDto> findAll();
    public PostReturnDto update(Long id, PostRequestDto postRequestDto);
    public void delete(Long id);
}
