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
 * 
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 友情链接id
     */
    @TableId("link_id")
    private String linkId;

    /**
     * 链接标题
     */
    @TableField("link_title")
    private String linkTitle;

    /**
     * 链接地址
     */
    @TableField("link_url")
    private String linkUrl;

    /**
     * 链接图标地址
     */
    @TableField("link_logo_url")
    private String linkLogoUrl;

    /**
     * 添加链接时间
     */
    @TableField("link_add_time")
    private LocalDateTime linkAddTime;


}
