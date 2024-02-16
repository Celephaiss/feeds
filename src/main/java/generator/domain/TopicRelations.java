package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 话题关联表
 * @TableName topic_relations
 */
@TableName(value ="topic_relations")
@Data
public class TopicRelations implements Serializable {
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
     * 话题 id
     */
    private Long tid;

    /**
     * 动态 id
     */
    private Long subjectId;

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