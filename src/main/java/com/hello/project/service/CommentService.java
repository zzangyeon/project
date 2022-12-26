package com.hello.project.service;

import com.hello.project.domain.article.Article;
import com.hello.project.domain.comment.Comment;
import com.hello.project.domain.user.User;
import com.hello.project.repository.UserRepository;
import com.hello.project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final UserRepository userRepository;

	@Transactional
	public Comment writeComment(Long articleId, String content, Long userId) {
		Article article = new Article();
		article.setId(articleId);
		User userEntity = userRepository.findById(userId).get();

		Comment comment = new Comment();
		comment.setArticle(article);
		comment.setUser(userEntity);
		comment.setContent(content);
		return commentRepository.save(comment);
	}

	@Transactional(readOnly = true)
	public List<Comment> getComment(Long articleId, Pageable pageable) {
		Slice<Comment> sliceComments = commentRepository.findAllByArticleId(articleId, pageable);
		return sliceComments.getContent();
	}

//	@Transactional(readOnly = true)
//	public Comment getOneComment(Long id) {
//		return commentRepository.findById(id).get();
//	}

	@Transactional
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}


}
