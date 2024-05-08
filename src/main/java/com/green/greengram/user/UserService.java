package com.green.greengram.user;

import com.green.greengram.common.model.CustomFileUtils;
import com.green.greengram.common.model.ResultDto;
import com.green.greengram.user.model.SignInPostReq;
import com.green.greengram.user.model.SignInRes;
import com.green.greengram.user.model.SignUpPostReq;
import com.green.greengram.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final CustomFileUtils customFileUtils;
    @Transactional
    public int postSignUp(MultipartFile pic, SignUpPostReq p) {
        //프로필 사진 설정
        String hashpw = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt()); //  유저가보낸 비밀번호를 암호화하여 hashgw 에 저장
        p.setUpw(hashpw);   // 비밀번호 해싱해서 세팅
        String saveFileName = customFileUtils.makeRandomFileName(pic); // 파일이름세팅
        p.setPic(saveFileName);

        int result = mapper.userPost(p);

        if (pic == null) {
            return result;
        }

        try {
            String path = String.format("user/%d", p.getUserId()); //폴더를 만들기 위한 path 경로설정
            customFileUtils.makeFolders(path);  // 폴더생성
            String target = String.format("%s/%s", path, saveFileName); // 파일을 폴더로 보내기 위한 타겟지정
            customFileUtils.transferTo(pic, target); // 파일전송
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("파일에러");
        }

        return result;
    }

    public SignInRes postSignIn(SignInPostReq p) {
        // 유저정보 가져오기
        User findUser = mapper.getUserById(p.getUid());
        if (findUser == null) { // 아이디값 비교
            throw new RuntimeException("");
        }
        if (!BCrypt.checkpw(p.getUpw(), findUser.getUpw())) { // 비밀번호 비교
            throw new RuntimeException("");
        }

        return SignInRes.builder()
                .userId(findUser.getUserId())
                .nm(findUser.getNm())
                .pic(findUser.getPic())
                .build();
    }
}
