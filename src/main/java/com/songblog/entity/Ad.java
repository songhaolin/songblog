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
 * 广告
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 广告id
     */
    @TableId("ad_id")
    private String adId;

    /**
     * 广告标题
     */
    @TableField("ad_title")
    private String adTitle;

    /**
     * 广告地址
     */
    @TableField("ad_url")
    private String adUrl;

    /**
     * 广告排序
     */
    @TableField("ad_sort")
    private Integer adSort;

    /**
     * 广告类型
     */
    @TableField("ad_type_id")
    private String adTypeId;

    /**
     * 广告开始时间
     */
    @TableField("ad_begin_time")
    private LocalDateTime adBeginTime;

    /**
     * 广告结束时间
     */
    @TableField("ad_end_time")
    private LocalDateTime adEndTime;

    /**
     * 添加广告时间
     */
    @TableField("ad_add_time")
    private LocalDateTime adAddTime;


}
