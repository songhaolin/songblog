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
 * 文章评论
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId("comment_id")
    private String commentId;

    /**
     * 文章id
     */
    @TableField("article_id")
    private String articleId;

    /**
     * 用户id（评论人）
     */
    @TableField("user_id")
    private String userId;

    /**
     * 评论时间
     */
    @TableField("comment_time")
    private LocalDateTime commentTime;

    /**
     * 点赞次数
     */
    @TableField("comment_good_number")
    private Integer commentGoodNumber;


}
