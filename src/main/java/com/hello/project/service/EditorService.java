package com.hello.project.service;

import com.hello.project.domain.article.Article;
import com.hello.project.domain.user.User;
import com.hello.project.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RequiredArgsConstructor
@Service
public class EditorService {

    private final ArticleRepository articleRepository;

    public void saveText(Long userId, String data) throws UnsupportedEncodingException {
        String title = URLDecoder.decode(data.substring(data.indexOf("=")+1,data.lastIndexOf("=")-5), "UTF-8");
        String content = URLDecoder.decode(data.substring(data.lastIndexOf("=")+1), "UTF-8");
        Article article = Article.builder().content(content).title(title).user(User.builder().id(userId).build()).build();
        articleRepository.save(article);
    }
}
