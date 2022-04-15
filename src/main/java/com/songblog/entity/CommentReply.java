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
 * 评论回复表
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论回复id
     */
    @TableId("comment_reply_id")
    private String commentReplyId;

    /**
     * 评论id
     */
    @TableField("comment_id")
    private String commentId;

    /**
     * 回复人id
     */
    @TableField("reply_user_id")
    private String replyUserId;

    /**
     * 被回复人id
     */
    @TableField("secondly_user_id")
    private String secondlyUserId;

    /**
     * 评论回复时间
     */
    @TableField("comment_reply_time")
    private LocalDateTime commentReplyTime;


}
