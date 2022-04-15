package com.songblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ArticleType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章类型id
     */
    @TableId("article_type_id")
    private String articleTypeId;

    /**
     * 文章分类父id
     */
    @TableField("article_type_parent_id")
    private String articleTypeParentId;

    /**
     * 文章分类名称
     */
    @TableField("article_type_name")
    private String articleTypeName;

    /**
     * 文章分类排序，越小越靠前
     */
    @TableField("article_type_sort")
    private Integer articleTypeSort;

    /**
     * 文章分类添加时间
     */
    @TableField("article_type_add_time")
    private LocalDateTime articleTypeAddTime;


}
