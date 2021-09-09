package com.hello.project.domain.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public List<Article> articleList(){
        PageRequest pageRequest = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC,"id"));
        Slice<Article> articleSlice = articleRepository.findAllBy(pageRequest);
        List<Article> articles = articleSlice.getContent();
        return articles;
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id).get();
    }
}
