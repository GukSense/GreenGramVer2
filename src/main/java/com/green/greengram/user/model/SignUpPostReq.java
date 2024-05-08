 package com.green.greengram.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SignUpPostReq {
    @Schema(example = "mic", description = "유저 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uid;
    @Schema(example = "1234", description = "유저 비밀번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;
    @Schema(example = "딸기초코파르페", description = "유저 닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nm;
    @JsonIgnore
    private String pic;
    @JsonIgnore
    private long userId;


}
