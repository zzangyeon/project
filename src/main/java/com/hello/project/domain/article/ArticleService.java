package com.hello.project.domain.article;

import com.hello.project.domain.comment.CommentRepository;
import com.hello.project.domain.user.User;
import com.hello.project.domain.user.UserRepository;
import com.hello.project.handler.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Value("${ck.path}")
    private String uploadFolder;

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
            Path thumbnailFilePath = Paths.get(uploadFolder+thumbnailFileName);
            try {
                Files.write(thumbnailFilePath, thumbnail.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        User user = new User(id);
        Article article = articleDto.toEntity(user,thumbnailFileName);
        return articleRepository.save(article);
    }

    @Transactional
    public Article updateArticle(ArticleUpdteDto articleUpdateDto) {

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
