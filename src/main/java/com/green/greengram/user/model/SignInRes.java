package com.green.greengram.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignInRes {
    @Schema(example = "1", description = "유저PK")
    private long userId;
    @Schema(example = "홍길동", description = "유저이름")
    private String nm;
    @Schema(example = "c450808c-48c")
    private String pic;
}
