package com.green.greengram.feed;

import com.green.greengram.common.model.CustomFileUtils;
import com.green.greengram.feed.model.*;
import com.green.greengram.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final CustomFileUtils customFileUtils;
    @Transactional
    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p) {
        int result = mapper.postFeed(p);
        log.info("p {}",p);
        FeedPicPostDto feedPicPostDto = FeedPicPostDto.builder().feedId(p.getFeedId()).build();
        try {
            String path = String.format("feed/%d", p.getFeedId());
            customFileUtils.makeFolders(path);

            for (MultipartFile pic : pics) {
                String saveFileName = customFileUtils.makeRandomFileName(pic);
                feedPicPostDto.getFileNames().add(saveFileName);
                String target = String.format("%s/%s", path, saveFileName);
                customFileUtils.transferTo(pic, target);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Feed 등록 오류!");
        }
        mapper.postFeedPics(feedPicPostDto);

        return FeedPostRes.builder()
                .feedId(p.getFeedId())
                .pics(feedPicPostDto.getFileNames())
                .build();
    }

    public List<FeedGetRes> getFeed(FeedGetReq p) {
        List<FeedGetRes> list = mapper.getFeed(p); // 리스트에 pic 값은 비어있다
        log.info("list: {}", list );
        for (FeedGetRes res: list) { // 1개의 리스트랑 list.size() (n) 만큼 돈다
            List<String> pics = mapper.getFeedPicsByFeedId(res.getFeedId()); //feedId 당 pic 갯수를 리스트저장
            res.setPics(pics); // 가져온 pic list 를 비어있는 pic 리스트에 넣음

        }
        log.info("list: {}", list );
        return list;
    }

}
