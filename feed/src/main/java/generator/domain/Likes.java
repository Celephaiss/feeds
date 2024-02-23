package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 点赞表
 * @TableName likes
 */
@TableName(value ="likes")
@Data
public class Likes implements Serializable {
    /**
     * 主键 snowflake id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 一级业务类型 如动态
     */
    private Integer biz;

    /**
     * 二级业务类型 如“动态正文”，“动态评论”，“动态回复”
     */
    private Integer subBiz;

    /**
     * 点赞人uid
     */
    private Integer fromUid;

    /**
     * 被点赞人uid
     */
    private Integer toUid;

    /**
     * 被点赞的主题 id
     */
    private Long sid;

    /**
     * 取消点赞状态 0 否 1 是
     */
    private Integer cancelStatus;

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
