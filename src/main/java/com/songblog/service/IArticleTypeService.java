package com.songblog.service;

import com.songblog.entity.ArticleType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.songblog.vo.ArticleTypeVo;

import java.util.List;

/**
 * <p>
 * 文章分类表 服务类
 * </p>
 *
 * @author jobob
 * @since 2022-04-07
 */
public interface IArticleTypeService extends IService<ArticleType> {

    /**
     * @Description: 文章类型列表，包含文章数
     * @Date: 2022/4/18

     **/
    List<ArticleTypeVo> articleTypeList();
}
