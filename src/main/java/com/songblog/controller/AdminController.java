package com.songblog.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.system.HostInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songblog.dto.user.UserDto;
import com.songblog.dto.user.UserLisePageDto;
import com.songblog.entity.Article;
import com.songblog.entity.User;
import com.songblog.service.IArticleService;
import com.songblog.service.IArticleTagService;
import com.songblog.service.IArticleTypeService;
import com.songblog.service.IUserService;
import com.songblog.utils.CommonPage;
import com.songblog.utils.CommonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.sql.Wrapper;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: shl
 * @Date: 2022/4/7
 **/
@Controller
@RequestMapping("/songblog")
public class AdminController {
    @Autowired
    private IArticleTypeService articleTypeService;
    @Autowired
    private IArticleTagService articleTagService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IUserService userService;
    /**
     * @Description: 管理端-基础数据
     * @Date: 2022/4/8
     * @Param model:
     **/
    @GetMapping("/")
    public String admin(Model model){
        //系统信息
        OsInfo osInfo = SystemUtil.getOsInfo();
        HostInfo  hostInfo = SystemUtil.getHostInfo();
        model.addAttribute("osName",osInfo.getName());
        model.addAttribute("hostName",hostInfo.getAddress());
        //文章信息
        int articleCount = articleService.count();
        int articleTypeCount = articleTypeService.count();
        int articleTagCount = articleTagService.count();
        model.addAttribute("articleCount",articleCount);
        model.addAttribute("articleTypeCount",articleTypeCount);
        model.addAttribute("articleTagCount",articleTagCount);
        //用户数量
        int userCount =userService.count();
        model.addAttribute("userCount",userCount);
        return "admin/index";
    }
    /**
     * @Description: 管理端-用户列表信息
     * @Date: 2022/4/8
     * @Param userLisePageDto:
     * @Param model:
     **/
    @GetMapping("/user/list")
    public String userList(@Valid UserLisePageDto userLisePageDto,Model model){
        //获取当前请求需要的页码和每页条数
        Integer pageNumber = userLisePageDto.getPageNumber();
        Integer pageSize = userLisePageDto.getPageSize();
        String userName = userLisePageDto.getUserName();
        //根据Ipage方法从user表中获取对应的信息
        IPage<User> userPage = new Page<>(pageNumber, pageSize);
        //利用Wrappers工具类创建wrapper对象，用来对user表中查出的数据做倒序排序，根据用户注册时间倒序排序，这个语法要记住
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.<User>lambdaQuery().orderByDesc(User::getUserRegisterTime);
        //如果username不为空，则在进行基于username的模糊查询
        if (StringUtils.isNotEmpty(userName)){
            userLambdaQueryWrapper.like(User::getUserName,userName);
            model.addAttribute("userName",userName);
        }
        //使用mybatisplus自带的page方法，输出userPage参数对象和查询顺序对象，获取page查询结果
        IPage<User> page = userService.page(userPage, userLambdaQueryWrapper);
        //将查出来的page结果格式化为项目通用的格式，存入model视图中，返回页面
        model.addAttribute("userPage", CommonPage.restPage(page));
        return "admin/userList";
    }
    /**
     * @Description: 管理-删除用户
     * @Date: 2022/4/13
     * @Param userId:
     **/
    @PostMapping("/user/del")
    @ResponseBody
    public CommonResult delUser(String userId){
        if (StringUtils.isEmpty(userId)){
            return CommonResult.failed("删除失败，请刷新页面重试");
        }
        //如果用户发布过文章，则不许删除用户，只能冻结用户
        //关联查询的userid，判断传入的userid在article表中的是否存在数据，存在，则count>0，不能删除数据
        if (articleService.count(Wrappers.<Article>lambdaQuery().eq(Article::getUserId,userId))>0){
            return CommonResult.failed("用户发布过文章，不允许删除，请冻结用户");
        }
        if (userService.removeById(userId)){
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败");
    }

    @PostMapping("/user/update")
    @ResponseBody
    public CommonResult updateUser(@Valid UserDto userDto){

        User user = userService.getById(userDto.getUserId());
        if (Objects.isNull(user)){
            return CommonResult.failed("用户信息不存在，请刷新页面重试");
        }
        String userPassword = userDto.getUserPassword();
        if (StrUtil.isNotBlank(userPassword)){
            userDto.setUserPassword(SecureUtil.md5(userPassword+user.getUserRegisterTime()));
        }else {
            userDto.setUserPassword(null);
        }
        BeanUtils.copyProperties(userDto,user);

        if (userService.updateById(user)){
            return CommonResult.success("更新成功");
        }
        return CommonResult.failed("更新失败");

    }
}
