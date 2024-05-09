package com.green.greengram.feedfavorite;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.feed.FeedService;
import com.green.greengram.feed.model.FeedPostRes;
import com.green.greengram.feedfavorite.model.FeedFavoriteToggleReq;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed/favorite")
public class FeedFavoriteController {
    private final FeedFavoriteService service;

    @GetMapping
    @Operation(summary = "좋아요", description = "Toggle 처리")
    public ResultDto<Integer> toggleFavorite(@ParameterObject @ModelAttribute FeedFavoriteToggleReq p) {
        int result = service.toggleFavorite(p);
        //result == 0 >> 좋아요 취소 (좋아요 -> 비좋아요) 좋아요 취소
        //       == 1 >> 좋아요 (비좋아요 -> 좋아요)  좋아요 처리

        String resultMsg = switch (result) {
            case 0 -> "좋아요 취소";
            case 1 -> "좋아요 처리";
            default -> "원치않는 값이 들어옴";
        };

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(resultMsg)
                .resultData(result)
                .build();
    }
}
