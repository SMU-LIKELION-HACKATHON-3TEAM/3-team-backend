package com.grishare.controller;

import com.grishare.domain.Post;
import com.grishare.domain.user.CustomUserDetail;
import com.grishare.dto.PostRequestDto;
import com.grishare.dto.PostReturnDto;
import com.grishare.service.PostService;
import com.grishare.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
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

    @GetMapping("/posts/{nationId}")
    public ResponseEntity<List<PostReturnDto>> getPostByNationId(@PathVariable("nationId") long nationId) {
        return ResponseEntity.ok(postService.findByNationId(nationId));
    }

    @GetMapping("/posts/{nationId}/{postId}")
    public ResponseEntity<PostReturnDto> getPostByPostId(@PathVariable("postId") long postId) {
        try {
            return ResponseEntity.ok(postService.findByPostId(postId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/posts/{nationId}")
    public ResponseEntity<Post> createPost(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable("nationId") long nationId,
            @RequestBody PostRequestDto postRequestDto) {
        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(postService.save(customUserDetail.getUser(), nationId, postRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
