package com.green.greengram.feed.model;

import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class FeedGetRes {
    private long feedId;
    private long writerId;
    private String writerNm;
    private String writerPic;
    private String contents;
    private String locations;
    private String createdAt;
    private int isFav;

    private List<String> pics;
    private List<FeedCommentGetRes> comments;
    private int isMoreComment;

}
