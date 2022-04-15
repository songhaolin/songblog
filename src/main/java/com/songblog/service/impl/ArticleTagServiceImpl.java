package com.songblog.service.impl;

import com.songblog.entity.ArticleTag;
import com.songblog.mapper.ArticleTagMapper;
import com.songblog.service.IArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章标签 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements IArticleTagService {

}
