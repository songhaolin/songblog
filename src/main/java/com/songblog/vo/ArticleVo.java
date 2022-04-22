package com.songblog.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class ArticleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * @Description: 用户名
     * @Date: 2022/4/22
     * @Param null:
     **/
    private String userName;
    /**
     * 关联文章id
     */
    private String articleTypeId;

    /**
     * 文章类型名称
     */
    private String articleTypeName;
    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 添加时间
     */
    private Date articleAddTime;

    /**
     * 点赞次数
     */
    private Integer articleGoodNumber;

    /**
     * 浏览次数
     */
    private Integer articleLookNumber;

    /**
     * 收藏次数
     */
    private Integer articleCollectionNumber;


}
