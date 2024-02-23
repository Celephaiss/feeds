package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName follow_cnt
 */
@TableName(value ="follow_cnt")
@Data
public class FollowCnt implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 关注数
     */
    private Integer followCnt;

    /**
     * 粉丝数
     */
    private Integer fansCnt;

    /**
     * 朋友数
     */
    private Integer friendCnt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}