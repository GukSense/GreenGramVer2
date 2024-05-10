package com.green.greengram.feedcomment;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.feedfavorite.FeedFavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed/comment")
public class FeedCommentController {
    private final FeedCommentService service;
    //전송 데이터 적음, 데이터 노출되면 안 되는 것
    @PostMapping
    public ResultDto<Long> postFeedComment(@RequestBody FeedCommentPostReq p) {
        long result = service.postFeedComment(p);//pk

        return ResultDto.<Long>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result).build();
    }

    @GetMapping
    public ResultDto<List<FeedCommentGetRes>> getFeedComment(@RequestParam(name = "feed_id") long feedId) {
        List<FeedCommentGetRes> list = service.getFeedComment(feedId); //더보기 클릭시 나올 댓글

        return ResultDto.<List<FeedCommentGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(String.format("row: %d", list.size()))
                .resultData(list).build();
    }
    @DeleteMapping
    public ResultDto<Integer> deleteFeedComment(@ParameterObject @ModelAttribute FeedCommentDeleteReq p) {
        int result = service.deleteFeedComment(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("")
                .resultData(result).build();
    }

}
