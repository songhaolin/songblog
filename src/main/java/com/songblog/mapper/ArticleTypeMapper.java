package com.songblog.mapper;

import com.songblog.entity.ArticleType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songblog.vo.ArticleTypeVo;

import java.util.List;

/**
 * <p>
 * 文章分类表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2022-04-07
 */
public interface ArticleTypeMapper extends BaseMapper<ArticleType> {

    /**
     * @Description: 文章类型列表，包含文章数
     * @Date: 2022/4/18

     **/
    List<ArticleTypeVo> articleTypeList();
}
