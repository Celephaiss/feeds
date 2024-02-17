package com.ailu.feeds.demos.web.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikeModelTest {

    @Resource
    LikeModel commentLikeModel;


//    @Test
//    void like() {
//        commentLikeModel.like(30010584, "1");
//    }
//
//
//    @Test
//    void unlike() {
//        commentLikeModel.unlike(30010584, "1");
//    }
//
//    @Test
//    void likeCount() {
//        System.out.println(commentLikeModel.likeCount("1"));
//    }

    @Test
    void isLike() {
        System.out.println(commentLikeModel.isLike(30010584L, 4L));
    }

    @Test
    void likeCount() {
        System.out.println(commentLikeModel.likeCount(4L));
    }
}
