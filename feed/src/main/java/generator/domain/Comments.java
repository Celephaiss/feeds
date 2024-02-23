package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comments
 */
@TableName(value ="comments")
@Data
public class Comments implements Serializable {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 业务id
     */
    private Integer biz;

    /**
     * 主题id
     */
    private Long subjectId;

    /**
     * 评论用户uid
     */
    private Integer uid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 发帖地址
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
