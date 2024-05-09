package com.green.greengram.feedcomment;

import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.feedfavorite.FeedFavoriteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedCommentService {
    private final FeedCommentMapper mapper;

    public long postFeedComment(FeedCommentPostReq p) {
        long affectedRow = mapper.postFeedComment(p);

        return p.getFeedCommentId();
    }

    public int deleteFeedComment(FeedCommentDeleteReq p) {
        return mapper.deleteFeedComment(p);
    }

}
