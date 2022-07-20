package com.hello.project.api;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.subscribe.Subscribe;
import com.hello.project.domain.subscribe.SubscribeRespDto;
import com.hello.project.domain.subscribe.SubscribeService;
import com.hello.project.dto.CMRespDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @ApiOperation(value = "구독하기", notes = "구독하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PostMapping("/api/subscribe/{toUserId}")
    public CMRespDto<?> subscribe(@PathVariable Long toUserId, @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Subscribe subscribe = subscribeService.saveSubscribe(principalDetails.getUser().getId(),toUserId);
        return new CMRespDto<>(1,"구독이 완료되었습니다.",null);
    }

    @ApiOperation(value = "구독 취소", notes = "구독 취소학기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @DeleteMapping("/api/subscribe/{toUserId}")
    public CMRespDto<?> unSubscribe(@PathVariable Long toUserId, @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails) {
        subscribeService.deleteSubscribe(principalDetails.getUser().getId(),toUserId);
        return new CMRespDto<>(1,"구독이 취소가 완료되었습니다.",null);
    }

    @ApiOperation(value = "구독 리스트", notes = "구독리스트 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/api/subscribe/{id}")
    public ResponseEntity<?> subscribeList(@PathVariable Long id) {
        List<SubscribeRespDto> subscribeList = subscribeService.subscribeList(id);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독자 정보 리스트 불러오기 성공",subscribeList), HttpStatus.OK);
    }
}
