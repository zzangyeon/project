package com.hello.project.api;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.subscribe.Subscribe;
import com.hello.project.domain.subscribe.SubscribeRespDto;
import com.hello.project.domain.subscribe.SubscribeService;
import com.hello.project.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/api/subscribe/{toUserId}")
    public CMRespDto<?> subscribe(@PathVariable Long toUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Subscribe subscribe = subscribeService.saveSubscribe(principalDetails.getUser().getId(),toUserId);
        return new CMRespDto<>(1,"구독이 완료되었습니다.",null);
    }

    @DeleteMapping("/api/subscribe/{toUserId}")
    public CMRespDto<?> unSubscribe(@PathVariable Long toUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        subscribeService.deleteSubscribe(principalDetails.getUser().getId(),toUserId);
        return new CMRespDto<>(1,"구독이 취소가 완료되었습니다.",null);
    }

    @GetMapping("/api/subscribe/{id}")
    public ResponseEntity<?> subscribeList(@PathVariable Long id) {
        List<SubscribeRespDto> subscribeList = subscribeService.subscribeList(id);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독자 정보 리스트 불러오기 성공",subscribeList), HttpStatus.OK);
    }
}
