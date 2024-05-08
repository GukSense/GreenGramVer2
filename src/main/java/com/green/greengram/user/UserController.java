package com.green.greengram.user;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "USER" ,description = "유저 CRUD")
public class UserController {
    private final UserService service;
    @PostMapping("sign-up")
    @Operation(summary = "회원가입", description = "")
    public ResultDto<Integer> postSignUp(@RequestPart(required = false) MultipartFile pic, @RequestPart SignUpPostReq p) {

        int result = service.postSignUp(pic, p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("회원가입 성공")
                .resultData(result)
                .build();
    }


    @PostMapping("sign-in")
    @Operation(summary = "인증처리", description = "")
    public ResultDto<SignInRes> postSignIn(@RequestBody SignInPostReq p) {

        SignInRes result = service.postSignIn(p);

        return ResultDto.<SignInRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("로그인 성공")
                .resultData(result)
                .build();
    }


}
