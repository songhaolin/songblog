package com.songblog;

import java.util.Date;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import com.songblog.entity.*;
import com.songblog.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
class SongblogApplicationTests {
    @Autowired
    private IUserService userService;
    @Autowired
    private IArticleTypeService articleTypeService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IArticleTagService articleTagService;
    @Autowired
    private IArticleTagListService articleTagListService;

    @Test
    void contextLoads() {

        List<User> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setUserName(i + "userName");
            user.setUserPassword(SecureUtil.md5("123456"));
            user.setUserFrozen(0);
            user.setUserRegisterTime(DateUtil.date());
            list.add(user);
        }
        userService.saveBatch(list, 50);
    }


    @Test
    void articleTypeInsert() {
        List<ArticleType> articleTypeList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ArticleType articleType = new ArticleType();
            articleType.setArticleTypeName("文章类型" + i + "" + i);
            articleType.setArticleTypeSort(i);
            articleType.setArticleTypeAddTime(new Date());
            articleTypeList.add(articleType);
        }
        articleTypeService.saveBatch(articleTypeList);

        ArrayList<Article> articles = new ArrayList<>();
        for (ArticleType articleType : articleTypeList) {
            for (int i = 0; i < 3; i++) {
                Article article = new Article();
                article.setArticleTypeId(articleType.getArticleTypeId());
                articles.add(article);
            }
        }
        articleService.saveBatch(articles);

        ArrayList<ArticleTag> articleTags = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleTagName("标签" + i);
            articleTag.setArticleTagAddTime(new Date());
            articleTags.add(articleTag);
        }
        articleTagService.saveBatch(articleTags);

        ArrayList<ArticleTagList> articleTagLists = new ArrayList<>();
        for (ArticleTag articleTag : articleTags) {
            for (int i = 0; i < 3; i++) {
                ArticleTagList articleTagList = new ArticleTagList();
                articleTagList.setArticleTagId(articleTag.getArticleTagId());
                articleTagList.setArticleId(articles.get(ThreadLocalRandom.current().nextInt(articles.size())).getArticleId());
                articleTagLists.add(articleTagList);
            }
        }
        articleTagListService.saveBatch(articleTagLists);
    }

}
