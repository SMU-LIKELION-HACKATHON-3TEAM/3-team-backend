package com.grishare.service;

import com.grishare.domain.Post;
import com.grishare.domain.image.PostImage;
import com.grishare.exception.CustomBadRequestException;
import com.grishare.exception.ErrorCode;
import com.grishare.repository.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    private String saveImage(String domainName, Long domainId, MultipartFile image) {
        String filename = image.getOriginalFilename();
        String projectPath = new File(".").getAbsolutePath();
        projectPath = projectPath.substring(0, projectPath.length() - 1) + "src/main/resources/static/";
        String filePath = projectPath + domainName + "/" + domainId.toString() + "_" + filename;
        try {
            // 파일 저장
            byte[] fileBytes = image.getBytes();
            Path imagePath = Paths.get(filePath);
            Files.write(imagePath, fileBytes);
        } catch (IOException e) {
            throw new CustomBadRequestException(ErrorCode.BAD_REQUEST);
        }
        return filePath;
    }

    public void savePostImages(Post post, List<MultipartFile> imageList) {
        if (imageList != null) {
            for (MultipartFile image : imageList) {
                if (!image.isEmpty()) {
                    String url = saveImage("post", post.getId(), image);
                    imageRepository.save(new PostImage(post, url));
                }
            }
        }
    }
}
