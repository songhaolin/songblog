package com.songblog.dto.user;

import com.songblog.dto.base.BasePageDto;
import lombok.Data;

/**
 * @Description:
 * @Author: shl
 * @Date: 2022/4/7
 **/
@Data
public class UserLisePageDto extends BasePageDto {
   /**
    * @Description: 用户名
    * @Date: 2022/4/7
    * @Param null:
    **/
    private String userName;

}
