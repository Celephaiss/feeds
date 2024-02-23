package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 关注计数表
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}