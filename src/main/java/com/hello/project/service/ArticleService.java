package com.hello.project.service;

import com.hello.project.domain.article.*;
import com.hello.project.repository.CommentRepository;
import com.hello.project.domain.user.User;
import com.hello.project.repository.UserRepository;
import com.hello.project.handler.exception.CustomException;
import com.hello.project.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final S3UploadService s3Upload;

    @Value("${ck.path}")
    private String uploadFolder;
    @Value("${cloud.ncp.s3.url}")
    private String s3Url;
    String thumbnailUrl = "";

    @Transactional(readOnly = true)
    public List<Article> articleList(Pageable pageable) {
//        PageRequest pageRequest = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC,"id"));
        Slice<Article> articleSlice = articleRepository.findAllBy(pageable);
        List<Article> articles = articleSlice.getContent();
        return articles;
    }

    @Transactional(readOnly = true)
    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> {
            throw new CustomException("잘못된 페이지 접근입니다.");
        });
    }

    @Transactional
    public Article saveArticle(ArticleDto articleDto, Long id) {

        String thumbnailFileName = "";
        MultipartFile thumbnail = articleDto.getThumbnail();

        if (thumbnail == null) {
            thumbnailFileName = "basic.jpg";
        }else {
            thumbnailFileName = UUID.randomUUID() + "_" + thumbnail.getOriginalFilename();
        }

        thumbnailUrl = s3Upload.upload(articleDto.getThumbnail());
        User user = new User(id);
        Article article = articleDto.toEntity(user,thumbnailUrl);
        return articleRepository.save(article);
    }

    @Transactional
    public Article updateArticle(ArticleUpdateDto articleUpdateDto) {

        Article articleEntity = articleRepository.findById(articleUpdateDto.getId()).get();

        if (!articleUpdateDto.getThumbnail().isEmpty()) {
            articleEntity.setThumbnailUrl(articleUpdateDto.getThumbnail().getOriginalFilename());
        }
        articleEntity.setTitle(articleUpdateDto.getTitle());
        articleEntity.setContent(articleUpdateDto.getContent());
        articleEntity.setDescription(articleUpdateDto.getDescription());
        return articleEntity;
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        commentRepository.deleteByArticleId(articleId);
        articleRepository.deleteById(articleId);
    }



}
