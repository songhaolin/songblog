package com.songblog.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * @Description:自定义分页数据分装类，使用page类格式化输入的数据
 * @Author: shl
 * @Date: 2022/4/7
 **/
@Data
public class CommonPage<T> {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;
    /**
     * @Description: 使用pageHelper将分页后的list数据封装
     * @Date: 2022/4/7
     * @Param list:
     **/
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> commonPage = new CommonPage<T>();
        Page<T> pageInfo = new Page<T>().setRecords(list);
        commonPage.setTotalPage((int)pageInfo.getPages());
        commonPage.setPageNumber((int)pageInfo.getCurrent());
        commonPage.setPageSize((int)pageInfo.getSize());
        commonPage.setTotal(pageInfo.getTotal());
        commonPage.setList(pageInfo.getRecords());
        return commonPage;
    }
    /**
     * @Description: 将springData分页后的list数据封装
     * @Date: 2022/4/7
     * @Param pageInfo:
     **/
    public static <T> CommonPage<T>restPage(IPage<T> pageInfo){
        CommonPage<T> commonPage = new CommonPage<T>();
        commonPage.setTotalPage((int)pageInfo.getPages());
        commonPage.setPageNumber((int)pageInfo.getCurrent());
        commonPage.setPageSize((int)pageInfo.getSize());
        commonPage.setTotal(pageInfo.getTotal());
        commonPage.setList(pageInfo.getRecords());
        return commonPage;
    }
}
