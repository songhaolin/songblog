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
import com.songblog.dto.article.ArticleDto;
import com.songblog.dto.article.ArticleTagDto;
import com.songblog.dto.article.ArticleTypeDto;
import com.songblog.dto.user.UserDto;
import com.songblog.dto.user.UserLisePageDto;
import com.songblog.entity.*;
import com.songblog.service.*;
import com.songblog.utils.CommonPage;
import com.songblog.utils.CommonResult;
import com.songblog.vo.ArticleTypeVo;
import com.songblog.vo.ArticleVo;
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
import java.util.Date;
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
    private IArticleTagListService articleTagListService;
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

    /**
     * @Description: 获取文章类型列表，包含文章数
     * @Date: 2022/4/18

     **/
    @GetMapping("/article/type/list")
    public String articleTypeList(Model model){
        List<ArticleTypeVo> articleTypeVoList = articleTypeService.articleTypeList();
        model.addAttribute("articleTypeVoList",articleTypeVoList);
        return "/admin/articleTypeList";
    }
    /**
     * @Description: 管理-更新文章类型
     * @Date: 2022/4/18
     * @Param articleTypeDto:
     **/
    @PostMapping("/article/type/addOrUpdate")
    @ResponseBody
    public CommonResult articleTypeUpdate(@Valid ArticleTypeDto articleTypeDto){
        ArticleType articleType = new ArticleType();
        BeanUtils.copyProperties(articleTypeDto,articleType);
        //前端传来的ID不为空则是更新
        if (StrUtil.isNotBlank(articleTypeDto.getArticleTypeId())){
            if (articleTypeService.updateById(articleType)){
                return CommonResult.success("更新成功！");
            }else {
                return CommonResult.failed("更新失败");
            }
        }
        //前端传来的id为空则是添加
        articleType.setArticleTypeAddTime(new Date());
        if (articleTypeService.save(articleType)){
            return CommonResult.success("添加成功！");
        }
        return CommonResult.failed("添加失败");
    }
    /**
     * @Description: 管理-文章类型删除
     * @Date: 2022/4/18
     * @Param articleTypeId:
     **/
    @PostMapping("/article/type/del")
    @ResponseBody
    public CommonResult articleTypeDel(String articleTypeId){
        //如果该文章类型下存在文章，则不许删除
        if(articleService.count(Wrappers.<Article>lambdaQuery().eq(Article::getArticleTypeId,articleTypeId))>0){
            return CommonResult.failed("该类型存在已发布文章，不允许删除。");
        }
        if (articleTypeService.removeById(articleTypeId)){
            return CommonResult.success("删除成功！");
        }
        return CommonResult.failed("删除失败！");
    }

    /**
     * @Description: 管理-文章标签列表
     * @Date: 2022/4/19
     * @Param model:
     **/
    @GetMapping("/article/tag/list")
    public String articleTagList(Model model){
        List<ArticleTag> articleTagList = articleTagService.list(Wrappers.<ArticleTag>lambdaQuery().orderByDesc(ArticleTag::getArticleTagAddTime));
        model.addAttribute("articleTagList",articleTagList);
        return "/admin/articleTagList";
    }
    /**
     * @Description: 管理-添加或者修改文章标签
     * @Date: 2022/4/19
     * @Param articleTagDto:
     **/
    @PostMapping("/article/tag/addOrUpdate")
    @ResponseBody
    public CommonResult articleTagAddOrUpdate(@Valid ArticleTagDto articleTagDto){
        ArticleTag articleTag = new ArticleTag();
        BeanUtils.copyProperties(articleTagDto,articleTag);
        if (StrUtil.isNotBlank(articleTagDto.getArticleTagId())){
            if (articleTagService.updateById(articleTag)){
                return CommonResult.success("更新成功");
            }
            return CommonResult.failed("更新失败！");
        }
        articleTag.setArticleTagAddTime(new Date());
        if (articleTagService.save(articleTag)){
            return CommonResult.success("添加成功！");
        }
        return CommonResult.failed("添加失败！");
    }
    /**
     * @Description: 管理-删除文章标签
     * @Date: 2022/4/19
     * @Param articleTagId:
     **/
    @PostMapping("/article/tag/del")
    @ResponseBody
    public CommonResult articleTagDel(String articleTagId){
        if(articleTagListService.count(Wrappers.<ArticleTagList>lambdaQuery().eq(ArticleTagList::getArticleTagId,articleTagId))>0){
            return CommonResult.failed("当前文章标签下存在已发布的文章，不允许删除！");
        }
        if (articleTagService.removeById(articleTagId)){
            return CommonResult.success("删除成功！");
        }
        return CommonResult.failed("删除失败！");
    }

    /**
     * @Description: 管理-文章列表
     * @Date: 2022/4/22

     **/
    @GetMapping("/article/list")
    public String articleList(@Valid ArticleDto articleDto, Model model){
        IPage<ArticleVo> articlePage = new Page<>(articleDto.getPageNumber(),articleDto.getPageSize());
        IPage<ArticleVo> articleVoIPage = articleService.articleList(articlePage,articleDto.getArticleTitle());
        model.addAttribute("articleTitle",articleDto.getArticleTitle());
        model.addAttribute("articleVoIPage",CommonPage.restPage(articleVoIPage));
        return "/admin/articleList";
    }
    /**
     * @Description: 管理-文章删除
     * @Date: 2022/4/22
     * @Param articleId:
     **/
    @PostMapping("/article/del")
    @ResponseBody
    public CommonResult articleDel(String articleId){
        if (articleService.removeById(articleId)){
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败");
    }
}
