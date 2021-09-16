package com.hello.project.domain.article;

import com.hello.project.domain.user.User;
import com.hello.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Value("${file.path}")
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
        return articleRepository.findById(id).get();
    }

    @Transactional
    public Article saveArticle(ArticleDto articleDto, Long id) {

        //UUID.randomUUID() + "_" +   추가하기.
        String thumbnailFileName = articleDto.getThumbnail().getOriginalFilename();
        Path thumbnailFilePath = Paths.get(uploadFolder + thumbnailFileName);
        try {
            Files.write(thumbnailFilePath,articleDto.getThumbnail().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        User userEntity = userRepository.findById(id).get();
        Article article = articleDto.toEntity(userEntity,thumbnailFileName);
        return articleRepository.save(article);
    }

    @Transactional
    public Article updateArticle(ArticleDto articleDto) {
        Article articleEntity = articleRepository.findById(articleDto.getId()).get();
        articleEntity.setTitle(articleDto.getTitle());
        articleEntity.setContent(articleDto.getContent());
        articleEntity.setDiscription(articleDto.getDiscription());
        return articleEntity;
    }

    @Transactional
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }



}
