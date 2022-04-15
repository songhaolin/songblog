package com.songblog.service.impl;

import com.songblog.entity.ArticleTagList;
import com.songblog.mapper.ArticleTagListMapper;
import com.songblog.service.IArticleTagListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章标签关联表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Service
public class ArticleTagListServiceImpl extends ServiceImpl<ArticleTagListMapper, ArticleTagList> implements IArticleTagListService {

}
