package com.hello.project.domain.comment;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> saveComment(
            @Valid @RequestBody CommentDto commentDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){
        //그냥 받으려면 x-www-form-urlencoded 방식이어야한다. json을 받으려면 @RequestBody써야함
        Comment comment = commentService.writeComment(commentDto.getArticleId(),commentDto.getContent(),principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글쓰기 성공", comment), HttpStatus.CREATED);
    }

    @GetMapping("/api/comment/{articleId}")
    public ResponseEntity<?> getComments(@PathVariable Long articleId, @PageableDefault(size = 4,sort = "id",direction = Sort.Direction.DESC)Pageable pageable) {
        List<Comment> comments = commentService.getComment(articleId, pageable);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글 추가 불러오기 성공", comments), HttpStatus.OK);
    }

//    @DeleteMapping("/api/comment/{id}")
//    public ResponseEntity<?> commentDelete(@PathVariable int id){
//        commentService.댓글삭제(id);
//        return new ResponseEntity<>(new CMRespDto<>(1,"댓글삭제 성공",null),HttpStatus.OK);
//    }
}
