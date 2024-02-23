package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 动态表 按照uid分区
 * @TableName feeds
 */
@TableName(value ="feeds")
@Data
public class Feeds implements Serializable {
    /**
     * 主键 snowflake id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 业务id
     */
    private Integer biz;

    /**
     * 用户 id
     */
    private Integer uid;

    /**
     * 内容
     */
    private String content;

    /**
     * 逗号分隔的 url “url1,url2,url3”
     */
    private String images;

    /**
     * 逗号分隔的话题id tid1，tid2，tid3
     */
    private String topicIds;

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
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
