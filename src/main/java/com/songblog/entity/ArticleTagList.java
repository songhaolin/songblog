package com.songblog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章标签关联表
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ArticleTagList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标签关联id
     */
    @TableId("article_tag_list_id")
    private String articleTagListId;

    /**
     * 文章id
     */
    @TableField("article_id")
    private String articleId;

    /**
     * 标签id
     */
    @TableField("article_tag_id")
    private String articleTagId;


}
