package com.green.greengram.feedcomment;

import com.green.greengram.common.GlobalConst;
import com.green.greengram.feed.FeedMapper;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.feedfavorite.FeedFavoriteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedCommentService {
    private final FeedCommentMapper mapper;
    private final FeedMapper feedMapper;

    public long postFeedComment(FeedCommentPostReq p) {
        int affectedRow = mapper.postFeedComment(p);

        return p.getFeedCommentId();
    }

    public int deleteFeedComment(FeedCommentDeleteReq p) {
        return mapper.deleteFeedComment(p);
    }

    public List<FeedCommentGetRes> getFeedComment(long feedId) {
        List<FeedCommentGetRes> feedComment = mapper.getFeedComment(feedId);
        feedComment.subList(0, GlobalConst.COMMENT_PAGING_SIZE - 1).clear();

        return feedComment;
    }
}
