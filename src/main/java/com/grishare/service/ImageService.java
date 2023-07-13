package com.grishare.service;

import com.grishare.domain.Post;
import com.grishare.domain.image.BackImage;
import com.grishare.domain.image.PostImage;
import com.grishare.domain.image.UserImage;
import com.grishare.domain.user.User;
import com.grishare.exception.CustomBadRequestException;
import com.grishare.exception.ErrorCode;
import com.grishare.repository.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public void saveUserImage(User user, MultipartFile image){
        if (!image.isEmpty()) {
            String url = saveImage("user", user.getId(), image);
            imageRepository.save(new UserImage(user, url));
        }
    }

    public void savebackImage(User user, MultipartFile image){
        if (!image.isEmpty()) {
            String url = saveImage("user", user.getId(), image);
            imageRepository.save(new BackImage(user, url));
        }
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

    private String saveImage(String domainName, Long domainId, MultipartFile image) {

        String relativePath = makeRelativePath(domainName, domainId, image);
        String entireFilePath = getAbsolutePath() + relativePath;
        System.out.println(entireFilePath);
        System.out.println(System.getProperty("user.dir"));
        try {
            File convertFile = new File(entireFilePath);

            if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
                FileOutputStream fos = new FileOutputStream(convertFile); // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(image.getBytes());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new CustomBadRequestException(ErrorCode.BAD_REQUEST);
        }
        return relativePath;
    }

    private String getAbsolutePath() {
        String projectPath = new File("").getAbsolutePath();
        return projectPath + "/src/main/resources/static";
    }

    private String makeRelativePath(String domainName, Long domainId, MultipartFile image) {
        return "/" + domainName + "/" + domainId.toString() + "_" + image.getOriginalFilename();
    }
}
