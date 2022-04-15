package com.songblog.service.impl;

import com.songblog.entity.ArticleType;
import com.songblog.mapper.ArticleTypeMapper;
import com.songblog.service.IArticleTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章分类表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-07
 */
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeMapper, ArticleType> implements IArticleTypeService {

}
