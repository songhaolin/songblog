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
 * 广告类型
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 广告类型id
     */
    @TableId("ad_type_id")
    private String adTypeId;

    /**
     * 广告标题
     */
    @TableField("ad_type_title")
    private String adTypeTitle;

    /**
     * 广告标识
     */
    @TableField("ad_type_tag")
    private String adTypeTag;

    /**
     * 广告类型排序，越小越靠前
     */
    @TableField("ad_type_sort")
    private Integer adTypeSort;

    /**
     * 广告添加时间
     */
    @TableField("ad_type_add_time")
    private LocalDateTime adTypeAddTime;


}
