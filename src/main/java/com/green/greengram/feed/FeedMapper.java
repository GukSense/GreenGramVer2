package com.green.greengram.feed;

import com.green.greengram.feed.model.*;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface FeedMapper {

    int postFeed(FeedPostReq p);
    List<FeedGetRes> getFeed(FeedGetReq p);

    List<String> getFeedPicsByFeedId(long feedId);
    int postFeedPics(FeedPicPostDto p);

    List<FeedCommentGetRes> getFeedCommentTopBy4ByFeedId(long feedId);
}
