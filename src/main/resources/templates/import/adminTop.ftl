<#include "./top.ftl">
<div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <!-- 导航头部 -->
            <div class="navbar-header">
                <!-- 移动设备上的导航切换按钮 -->
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target=".navbar-collapse-example">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- 品牌名称或logo -->
                <a class="navbar-brand" href="/">首页</a>
            </div>
            <!-- 导航项目 -->
            <div class="collapse navbar-collapse navbar-collapse-example">
                <!-- 一般导航项目 -->
                <ul class="nav navbar-nav">
                    <#--                    每个li代表一个导航选项-->
                    <#-- 1.基础数据，不含下拉菜单的-->
                    <li><a href="/songblog/">基础数据</a></li>
                    ...
                    <#-- 2.用户管理，使用下拉菜单的样式-->
                    <!-- 导航中的下拉菜单 -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">用户管理 <b class="caret"></b></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/songblog/user/list">用户列表</a></li><!--必须以/开头，否则会追加在上面页面的url后面导致不可达-->
                        </ul>
                    </li>
                    <#-- 3.文章管理，使用下拉菜单的样式-->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">文章管理 <b class="caret"></b></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/songblog/article/type/list">文章类型</a></li>
                            <li><a href="/songblog/article/tag/list">文章标签</a></li>
                            <li><a href="/songblog/article/list">文章管理</a></li>
                        </ul>
                    </li>
                    <#-- 4.友情链接，不含下拉菜单的-->
                    <li><a href="/songblog/link">友情链接</a></li>
                    <#-- 5，广告管理，不含下拉菜单的-->
                    <li><a href="/songblog/ad">广告管理</a></li>
                </ul>
                <!-- 6.退出登录按钮，参看zui的导航栏组件--》右侧的导航项目 -->
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="your/nice/url"><i class="icon icon-signout"></i> 退出登录</a></li>
                    <#--                        导航栏的下拉框选项-->
                    <#--                        <li class="dropdown">-->
                    <#--                            <a href="your/nice/url" class="dropdown-toggle" data-toggle="dropdown">探索 <b class="caret"></b></a>-->
                    <#--                            <ul class="dropdown-menu" role="menu">-->
                    <#--                                <li><a href="your/nice/url">敏捷开发</a></li>-->
                    <#--                                <li><a href="your/nice/url">HTML5</a></li>-->
                    <#--                                <li><a href="your/nice/url">Javascript</a></li>-->
                    <#--                                <li class="divider"></li>-->
                    <#--                                <li><a href="your/nice/url">探索宇宙</a></li>-->
                    <#--                            </ul>-->
                    <#--                        </li>-->
                </ul>
            </div><!-- END .navbar-collapse -->
        </div>
    </nav>