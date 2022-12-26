package com.hello.project.api;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.comment.Comment;
import com.hello.project.domain.comment.CommentDto;
import com.hello.project.service.CommentService;
import com.hello.project.dto.CMRespDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @ApiOperation(value = "댓글 등록", notes = "댓글 등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok~!"),
            @ApiResponse(code = 404, message = "page not found~!")
    })
    @PostMapping("/api/comment")
    public ResponseEntity<?> saveComment(
            @RequestBody @Valid CommentDto commentDto, BindingResult bindingResult,
            @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails){
        //그냥 받으려면 x-www-form-urlencoded 방식이어야한다. json을 받으려면 @RequestBody써야함

        Comment comment = commentService.writeComment(commentDto.getArticleId(),commentDto.getContent(),principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글쓰기 성공", comment), HttpStatus.CREATED);
    }

    @ApiOperation(value = "댓글 불러오기", notes = "댓글 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok~!"),
            @ApiResponse(code = 404, message = "page not found~!")
    })
    @GetMapping("/api/comment/{articleId}")
    public ResponseEntity<?> getComments(@PathVariable Long articleId,
                                         @ApiIgnore @PageableDefault(size = 4,sort = "id",direction = Sort.Direction.DESC)Pageable pageable) {

        List<Comment> comments = commentService.getComment(articleId, pageable);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글 추가 불러오기 성공", comments), HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글 삭제하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){

        commentService.deleteComment(id);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글삭제 성공",id),HttpStatus.OK);
    }
}
