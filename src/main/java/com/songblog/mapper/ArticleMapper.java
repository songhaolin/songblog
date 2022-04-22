package com.songblog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songblog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songblog.vo.ArticleVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<ArticleVo> articleList(IPage<ArticleVo> articlePage,@Param("articleTitle")  String articleTitle);
}
