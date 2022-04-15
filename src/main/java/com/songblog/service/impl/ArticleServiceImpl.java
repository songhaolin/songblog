package com.songblog.service.impl;

import com.songblog.entity.Article;
import com.songblog.mapper.ArticleMapper;
import com.songblog.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
