package generator.service;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


@MybatisTest
class FollowCntServiceTest {

    @Autowired
    private FollowCntService followCntService;

    @Test
    void test() {
        System.out.println(followCntService);
    }

}
