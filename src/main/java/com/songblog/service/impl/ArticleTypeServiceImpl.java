package com.songblog.service.impl;

import com.songblog.entity.ArticleType;
import com.songblog.mapper.ArticleTypeMapper;
import com.songblog.service.IArticleTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songblog.vo.ArticleTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    /**
     * @Description: 文章类型列表，包含文章数
     * @Date: 2022/4/18

     **/
    @Override
    public List<ArticleTypeVo> articleTypeList() {
        return articleTypeMapper.articleTypeList();
    }
}
