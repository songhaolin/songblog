package com.songblog.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 文章分类表
 * </p>
 *
 * @author jobob
 * @since 2022-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ArticleTypeVo {


    /**
     * 文章类型id
     */
    private String articleTypeId;

    /**
     * 文章分类父id
     */
    private String articleTypeParentId;

    /**
     * 文章分类名称
     */
    private String articleTypeName;

    /**
     * 文章分类排序，越小越靠前
     */
    private Integer articleTypeSort;

    /**
     * 文章分类添加时间
     */
    private Date articleTypeAddTime;

    /**
     * @Description: 文章类型关联的文章数
     * @Date: 2022/4/18
     * @Param null:
     **/
    @TableField(exist = false)//表示该字段不是表中包含的字段
    private Integer articleCount;

}
