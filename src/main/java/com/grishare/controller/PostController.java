package com.grishare.controller;

import com.grishare.domain.Post;
import com.grishare.dto.PostRequestDto;
import com.grishare.dto.PostReturnDto;
import com.grishare.service.PostService;
import com.grishare.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<PostReturnDto>> getPostById(@PathVariable("nationId") long nationId) {
        return ResponseEntity.ok(postService.findByNationId(nationId));
    }

    @PostMapping("/posts/{nationId}")
    public ResponseEntity<Post> createPost(
            @PathVariable("nationId") long nationId,
            @RequestBody PostRequestDto postRequestDto) {
        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(postService.save(nationId, postRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/posts/{id}")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<PostReturnDto> updatePost(
            @PathVariable("id") long id,
            @RequestBody PostRequestDto postRequestDto
    ) {
        try {
            ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(postService.update(id, postRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            postService.delete(id);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
