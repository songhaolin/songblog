package com.songblog.dto.base;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: shl
 * @Date: 2022/4/7
 **/
@Data
public class BasePageDto {
    /**
     * @Description: 当前页数
     * @Date: 2022/4/7
     * @Param null:
     **/
    @NotNull(message = "当前页码不能为空")
    private Integer pageNumber = 1;
    /**
     * @Description: 每页条数
     * @Date: 2022/4/7
     * @Param null:
     **/
    private Integer pageSize = 20;
}
