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
        List<User> users = userService.list();


        List<ArticleType> articleTypeList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ArticleType articleType = new ArticleType();
            articleType.setArticleTypeName("文章分类" + i);
            articleType.setArticleTypeSort(10);
            articleType.setArticleTypeAddTime(new Date());
            articleTypeList.add(articleType);
        }
        articleTypeService.saveBatch(articleTypeList);

        ArrayList<Article> arrayList = new ArrayList<>();
        for (ArticleType articleType : articleTypeList) {
            for (int i = 0; i < 6; i++) {
                Article article = new Article();
                article.setArticleTypeId(articleType.getArticleTypeId());
                article.setUserId(users.get(ThreadLocalRandom.current().nextInt(users.size())).getUserId());
                article.setArticleTitle("文章标题：" + i);
                article.setArticleAddTime(DateUtil.date());
                article.setArticleContent("文章内容：" + ThreadLocalRandom.current().nextInt(1000));
                article.setArticleGoodNumber(0);
                article.setArticleLookNumber(0);
                article.setArticleCollectionNumber(0);
                arrayList.add(article);
            }
        }
        articleService.saveBatch(arrayList, 50);


        ArrayList<ArticleTag> articleTags = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleTagName("文章标签：" + i);
            articleTag.setArticleTagAddTime(DateUtil.date());
            articleTags.add(articleTag);
        }
        articleTagService.saveBatch(articleTags, 50);


        ArrayList<ArticleTagList> articleTagLists = new ArrayList<>();
        for (ArticleTag articleTag : articleTags) {
            for (int i = 0; i < 3; i++) {
                ArticleTagList articleTagList = new ArticleTagList();
                articleTagList.setArticleId(arrayList.get(ThreadLocalRandom.current().nextInt(arrayList.size())).getArticleId());
                articleTagList.setArticleTagId(articleTag.getArticleTagId());
                articleTagLists.add(articleTagList);
            }
        }
        articleTagListService.saveBatch(articleTagLists, 50);
    }

}
