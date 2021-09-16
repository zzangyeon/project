package com.hello.project.domain.user;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/user/{id}")
    public CMRespDto<?> userUpdate(
            @PathVariable Long id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User userEntity = userService.userUpdate(id, userUpdateDto.toEntity());
        principalDetails.setUser(userEntity);
        return new CMRespDto<>(1, "회원정보 수정완료", userEntity);
    }


//    @PutMapping("/api/user/{id}")
//    public CMRespDto<?> update(
//            @PathVariable int id,
//            @Valid UserUpdateDto userUpdateDto,
//            BindingResult bindingResult,//꼭 @Valid 가 적혀있는 다음 파라미터에 적어야한다!!!
//            @AuthenticationPrincipal PrincipalDetails principalDetails){//CMRespDto이용해 api로 ajax응답...
//
//        User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
//        principalDetails.setUser(userEntity);//세션정보수정 - 세션에도 바뀐 userEntity정보를 저장해 주어야 다시 회원정보수정에 들어갔을때 반영이 된다.
//        return new CMRespDto<>(1,"회원수정완료",userEntity);//응답시 userEntity의 모든 getter함수가 호출되고 JSON으로 파싱하여 응답한다.JSON으로 파싱시 메세지 컨버터가 모든 getter함수를 호출 실행한다.
//        //userEntity 리턴시 getter함수로 호출하고 그것을 JSON으로 바꾸는 과정에서 User 의 List<Image> images가 getter로 인해 계속 list에 데이터가 담기고 그것을 JSON으로 바꾸고 하는 과정에서 무한반복이 일어난다!
//    }


}
