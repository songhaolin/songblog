package com.songblog;
import java.util.Date;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import com.songblog.entity.User;
import com.songblog.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SongblogApplicationTests {
    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {

        List<User> list = new ArrayList<>();
        for (int i=0;i<50;i++){
            User user = new User();
            user.setUserName(i+"userName");
            user.setUserPassword(SecureUtil.md5("123456"));
            user.setUserFrozen(0);
            user.setUserRegisterTime(DateUtil.date());
            list.add(user);
        }
        userService.saveBatch(list,50);
    }

}
