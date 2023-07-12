package com.grishare.service;

import com.grishare.domain.Comment;
import com.grishare.domain.Nation;
import com.grishare.domain.Post;
import com.grishare.domain.user.User;
import com.grishare.dto.PostDetailReturnDto;
import com.grishare.dto.PostRequestDto;
import com.grishare.dto.PostReturnDto;
import com.grishare.exception.CustomNotFoundException;
import com.grishare.exception.ErrorCode;
import com.grishare.repository.NationRepository;
import com.grishare.repository.PostRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final NationRepository nationRepository;

    @Override
    public Post save(User user, Long nationId, PostRequestDto postRequestDto) {
        Nation nation = nationRepository.findById(nationId).orElseThrow(() -> {
            throw new CustomNotFoundException(ErrorCode.NOT_FOUND);
        });
        return postRepository
                .save(
                        postRequestDto.toEntity(user, nation, Comment.builder().build())
                );
    }

    @Override
    public List<PostReturnDto> findByNationId(Long nationId) {
        List<Post> postList = postRepository.findAllByNationId(nationId);
        return postList.stream().map(PostReturnDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostDetailReturnDto findByPostId(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new CustomNotFoundException(ErrorCode.NOT_FOUND);
        });

        post.setView(post.getView() + 1);

        return new PostDetailReturnDto(post);
    }

    @Override
    public List<PostReturnDto> findAll() {
        List<Post> posts = postRepository.findAll();

        List<PostReturnDto> postReturnDtoList =
                posts.stream().map(post -> new PostReturnDto(post))
                        .collect(Collectors.toList());
        return postReturnDtoList;
    }

    @Transactional
    @Override
    public PostReturnDto update(Long id, PostRequestDto postRequestDto) {
        try {
            Optional<Post> postData = postRepository.findById(id);
            if (postData.isPresent()) {
                Post _post = postData.get();
                _post.setTitle(postRequestDto.getTitle());
                _post.setContent(postRequestDto.getContent());
                return new PostReturnDto(_post);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
