package com.grishare.controller;

import com.grishare.domain.Post;
import com.grishare.domain.user.CustomUserDetail;
import com.grishare.dto.PostDetailReturnDto;
import com.grishare.dto.PostRequestDto;
import com.grishare.dto.PostReturnDto;
import com.grishare.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostReturnDto>> getAllPost() {
        List<PostReturnDto> posts = postService.findAll();

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/nation/{nationId}")
    public ResponseEntity<List<PostReturnDto>> getPostByNationId(@PathVariable("nationId") long nationId) {
        return ResponseEntity.ok(postService.findByNationId(nationId));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailReturnDto> getPostByPostId(@PathVariable("postId") long postId) {
        try {
            return ResponseEntity.ok(postService.findByPostId(postId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/posts/{nationId}")
    public ResponseEntity<PostReturnDto> createPost(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable("nationId") long nationId,
            @RequestPart PostRequestDto postRequestDto,
            @RequestPart(required = false) List<MultipartFile> imageFiles) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.save(customUserDetail.getUser(), nationId, postRequestDto, imageFiles));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostReturnDto> updatePost(
            @PathVariable("postId") long postId,
            @RequestBody PostRequestDto postRequestDto
    ) {
        try {
            ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(postService.update(postId, postRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("postId") long postId) {
        try {
            postService.delete(postId);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
