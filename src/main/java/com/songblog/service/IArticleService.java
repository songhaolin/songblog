package com.songblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songblog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.songblog.vo.ArticleVo;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
public interface IArticleService extends IService<Article> {

    IPage<ArticleVo> articleList(IPage<ArticleVo> articlePage, String articleTitle);
}
