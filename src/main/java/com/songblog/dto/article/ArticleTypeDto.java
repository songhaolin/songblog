package com.songblog.dto.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class ArticleTypeDto implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @NotBlank(message = "文章类型名称不能为空")
    private String articleTypeName;

    /**
     * 文章分类排序，越小越靠前
     */
    @NotNull(message = "文章类型排序不能为空")
    private Integer articleTypeSort;

    /**
     * 文章分类添加时间
     */
    private Date articleTypeAddTime;


}
