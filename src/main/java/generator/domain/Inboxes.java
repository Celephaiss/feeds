package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户收件箱
 * @TableName inboxes
 */
@TableName(value ="inboxes")
@Data
public class Inboxes implements Serializable {
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
     * 发件人uid
     */
    private Integer fromUid;

    /**
     * 收件人uid
     */
    private Integer toUid;

    /**
     * 主题id
     */
    private Integer subjectId;

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