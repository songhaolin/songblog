package com.songblog.dto.article;

import com.songblog.dto.base.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
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
public class ArticleDto extends BasePageDto {

    /**
     * 文章标签id
     */
    private String articleId;

    /**
     * 标签名称
     */
    private String articleTitle;

}
