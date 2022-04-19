package com.songblog.dto.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 文章标签
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ArticleTagDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标签id
     */
    private String articleTagId;

    /**
     * 标签名称
     */
    @NotBlank(message = "文章标签名称不能为空")
    private String articleTagName;

    /**
     * 添加时间
     */
    private Date articleTagAddTime;
}
