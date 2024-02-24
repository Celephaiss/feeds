package com.ailu.follow;


import com.ailu.follow.service.FollowService;
import com.ailu.follow.vo.FollowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;


    @PostMapping("/follow")
    public String follow(@RequestBody FollowVo followVo) {
        followService.follow(followVo.getFromUid(), followVo.getToUid());
        return "follow";
    }

    @PostMapping("/unfollow")
    public String unfollow(@RequestBody FollowVo followVo) {
        followService.unFollow(followVo.getFromUid(), followVo.getToUid());
        return "unfollow";
    }

}
