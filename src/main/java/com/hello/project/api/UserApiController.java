package com.hello.project.api;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.user.User;
import com.hello.project.domain.user.UserService;
import com.hello.project.domain.user.UserUpdateDto;
import com.hello.project.dto.CMRespDto;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @ApiOperation(value = "User Update", notes = "유저 업데이트")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok~!"),
            @ApiResponse(code = 404, message = "page not found~!")
    })
    @Hidden
    @PostMapping("/api/user/{id}")
    public CMRespDto<?> userUpdate(
            @ApiParam(value = "userId", required = true, example = "1") @PathVariable Long id,
            @ApiIgnore @Valid UserUpdateDto userUpdateDto, BindingResult bindingResult,
            @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails) {

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
