package generator.mapper;

import com.ailu.feeds.FeedsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = FeedsApplication.class)
class RepliesMapperTest {

    @Autowired
    RepliesMapper repliesMapper;

    @Test
    void getTopNReplies() {

        List<Long> list = Arrays.asList(2L, 3L);
        System.out.println(repliesMapper.getTopNReplies(list));
    }

}
