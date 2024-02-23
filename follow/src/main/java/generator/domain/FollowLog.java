package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 关注流水表
 * @TableName follow_log
 */
@TableName(value ="follow_log")
@Data
public class FollowLog implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 目标id
     */
    private Integer target;

    /**
     * 客户端ip
     */
    private Long ip;

    /**
     * 1-关注，2-取消关注
     */
    private Integer opType;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}