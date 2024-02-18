package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 回复表
 * @TableName replies
 */
@TableName(value ="replies")
@Data
public class Replies implements Serializable {
    /**
     * 回复id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 业务id
     */
    private Integer biz;

    /**
     * 回复类型 0 回复“评论” 1 回复“回复”
     */
    private Integer typ;

    /**
     * 主题id  冗余的动态 id
     */
    private Long subjectId;

    /**
     * 回复的评论id
     */
    private Long commentId;

    /**
     * 回复的回复id
     */
    private Long replyId;

    /**
     * 回复用户id
     */
    private Integer fromUid;

    /**
     * 被回复用户id
     */
    private Integer toUid;

    /**
     * 回复内容
     */
    private String content;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 删除 0 否 1 是
     */
    private Integer deleted;

    /**
     * 删除原因 0 未删除 1 用户主动删除 2 审核不通过删除
     */
    private Integer deletedReason;

    /**
     * 审核状态 0 审核中 1 审核通过 2 审核不通过
     */
    private Integer auditStatus;

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