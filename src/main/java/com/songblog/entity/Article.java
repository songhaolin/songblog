package com.songblog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @TableId("article_id")
    private String articleId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 文章标题
     */
    @TableField("article_title")
    private String articleTitle;

    /**
     * 文章内容
     */
    @TableField("article_content")
    private String articleContent;

    /**
     * 添加时间
     */
    @TableField("article_add_time")
    private LocalDateTime articleAddTime;

    /**
     * 点赞次数
     */
    @TableField("article_good_number")
    private Integer articleGoodNumber;

    /**
     * 浏览次数
     */
    @TableField("article_look_number")
    private Integer articleLookNumber;

    /**
     * 收藏次数
     */
    @TableField("article_collection_number")
    private Integer articleCollectionNumber;


}
