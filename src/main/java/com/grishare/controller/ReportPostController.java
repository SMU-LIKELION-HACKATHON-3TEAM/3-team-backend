package com.grishare.controller;

import com.grishare.dto.ReportPostRequestDto;
import com.grishare.service.PostServiceImpl;
import com.grishare.service.ReportPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReportPostController {
    @Autowired
    ReportPostServiceImpl reportPostService;

    PostServiceImpl postService;

//    @PostMapping("/post/{countryCode}/{postId}/report")
//    public ResponseEntity<?> reportPost(@PathVariable("countryCode") String countryCode, @PathVariable("postId") Long postId, @RequestBody ReportPostRequestDto reportPostRequestDto){
//        try {
//            return ResponseEntity
//                    .status(HttpStatus.CREATED)
//                    .body(reportPostService.save(postId, reportPostRequestDto));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @PostMapping("/post/report/{postId}")
    public ResponseEntity<?> reportPost(@PathVariable("postId") Long postId, @RequestBody ReportPostRequestDto reportPostRequestDto){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(reportPostService.save(postId, reportPostRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
