package com.hello.project.domain.comment;

import com.hello.project.domain.article.Article;
import com.hello.project.domain.user.User;
import com.hello.project.domain.user.UserRepository;
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

	@Transactional(readOnly = true)
	public Comment getOneComment(Long id) {
		return commentRepository.findById(id).get();
	}

	@Transactional
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}

//Tip 객체를 직접 만들어서 id값만 담아서 insert 할 수 있다.! 이렇게 하지 않으면 imagerepository.findById() 로 db접근해서 찾아와야한다!
//어차피 db에 들어가는 값은 id값이고, 우리가 comment에서 필요한것은 pk(id)이기 때문에 괜찮다!
//대신!!! return시 image객체와 user객체는 id값만 가지고 있는 빈 객체를 리턴받는다.

//	@Transactional
//	public void 댓글삭제(int id) {
//		try {//오류가 터지면 이렇게 try catch로 해준다!
//			commentRepository.deleteById(id);
//		} catch (Exception e) {
//			throw new CustomApiException(e.getMessage());
//		}
//	}

}
