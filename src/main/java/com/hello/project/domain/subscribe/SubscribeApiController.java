package com.hello.project.domain.subscribe;

import com.hello.project.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/api/subscribe")
    public CMRespDto<?> subscribe(SubscribeDto subscribeDto) {
        System.out.println("=======subscribe controller=========");
        Subscribe subscribe = subscribeService.saveSubscribe(subscribeDto);
        return new CMRespDto<>(1,"구독이 완료되었습니다.",null);
    }
}
