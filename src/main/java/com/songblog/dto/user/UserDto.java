package com.songblog.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: shl
 * @Date: 2022/4/14
 **/
@Data
public class UserDto {

    /**
     * 用户id
     */
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户是否冻结 0正常 1冻结
     */
    private Integer userFrozen;
}
