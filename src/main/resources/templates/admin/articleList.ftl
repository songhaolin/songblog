<!DOCTYPE html>
<#include "../import/adminTop.ftl">
<!--判断返回的数据是否为空且list的size属性是否大于0，满足条件则进入-->
<div class="panel">
    <div class="panel-body">

        <form class="form-horizontal" action="/songblog/article/list" method="get">
            <div class="form-group">
                <label for="articleTitle" class="col-sm-1">文章标题</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control " value="${articleTitle!}" id="articleTitle" name="articleTitle"
                           placeholder="文章标题">
                </div>
                <div class="col-sm-1"><!--class="btn btn-success" 是将当前1个单位的组件变成成功样式的按钮-->
                    <button type="submit" class="btn btn-success"><i class="icon icon-search"></i>搜索</button>
                </div>
                <div class="col-sm-1"><!--a表示这是一个可以点击的链接，点击直接跳转到对应的后端接口-->
                    <a href="/songblog/article/list" class="btn btn-success"><i class="icon icon-search"></i>全部</a>
                </div>
            </div>
        </form>

    </div>
</div>
<#if articleVoIPage??&&articleVoIPage.list?size gt 0>

<!--面板+表格-->
<div class="panel">
    <div class="panel-body">
        <table class="table">
            <thead>
            <tr><!--tr表示表头信息-->
                <th>文章标题</th>
                <th>文章类型</th>
                <th>发布者</th>
                <th>发布时间</th>
                <th>浏览量</th>
                <th>点赞量</th>
                <th>收藏量</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody><!--tbody表示表格填充信息,就是填充数据的位置-->
            <#list articleVoIPage.list as articleVo>
                <tr>
                    <td>${articleVo.articleTitle!}</td>
                    <td>${articleVo.articleTypeName!}</td>
                    <td>${articleVo.userName!}</td>
                    <td>
                        ${(articleVo.articleAddTime)?string("yyyy-MM-dd HH:mm:ss")}
                    </td>
                    <td>${articleVo.articleLookNumber!}</td>
                    <td>${articleVo.articleGoodNumber!}</td>
                    <td>${articleVo.articleCollectionNumber!}</td>
                    <td>
                        <!--设置操作按钮，button为按钮语法，onclick表示触发事件，type表示按钮类型，class设置按钮格式-->
                        <button onclick="delArticle('${(articleVo.articleId)!}')" type="button" class="btn btn-mini">
                            <i class="icon icon-remove"></i>删除
                        </button>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <!--加入上下页组件,此为手写的一个上下页功能-->
    <div class="panel">
        <div class="panel-body" style="padding: 0;">
            <div class="col-sm-12" style="padding: 0;text-align: center">
                <ul class="pager" style="margin-top: 10px;margin-bottom: 10px">
                    <!--添加最前页按钮-->
                    <li class="previous" onclick="getNewData(1)">
                        <a href="javascript:void(0)"><i class="icon icon-backward"></i> </a>
                    </li>
                    <!--处理上一页的功能-->
                    <#if articleVoIPage.pageNumber lte 1>
                        <li class="previous.disabled">
                            <a href="javascript:void(0)"><i class="icon icon-chevron-left"></i></a>
                        </li>
                    <#else>
                        <li class="previous" onclick="getNewData('${articleVoIPage.pageNumber-1}')">
                            <a href="javascript:void(0)"><i class="icon icon-chevron-left"></i></a>
                        </li>
                    </#if>
                    <!--处理当前页和总页展示-->
                    <li>
                        <a href="javascript:void(0)" class="btn">${articleVoIPage.pageNumber}
                            页/共${articleVoIPage.totalPage}页
                        </a>
                    </li>
                    <!--处理下一页功能-->
                    <#if articleVoIPage.pageNumber gte articleVoIPage.totalPage>
                        <li class="next disabled">
                            <a href="javascript:void(0)"><i class="icon icon-chevron-right"></i></a>
                        </li>
                    <#else >
                        <li class="next" onclick="getNewData('${articleVoIPage.pageNumber+1}')">
                            <a href="javascript:void(0)"><i class="icon icon-chevron-right"></i></a>
                        </li>
                    </#if>
                    <!--展示最后一页-->
                    <li class="next" onclick="getNewData('${articleVoIPage.totalPage}')">
                        <a href="javascript:void(0)"><i class="icon icon-forward"></i> </a>
                    </li>
                    <!--添加跳转页输入框，只允许输入数字-->
                    <li class="next">
                        <a href="javascript:void(0)">
                            <input type="number" id="renderPageNumber" maxlength="5"
                                   style="width:50px;height: 20px;" oninput="value=value.replace(/[^\d]/g,'')">
                        </a>
                    </li>
                    <li class="next">
                        <a href="javascript:void(0)" onclick="renderPage()"
                           style="padding-left: 2px;padding-right: 2px;">
                            跳转
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</div>
<#else ><!--不满足条件，则弹出一个提示图标和提示语-->
<div class="panel">
    <div class="panel-body">
        <div style="text-align: center"><!--div style表示设置组件的格式，text-align设置文本对齐方式，center为居中-->
            <h3><i class="icon icon-coffee"></i></h3>
            <h3>暂无数据</h3>
        </div>
    </div>
</div>
</#if>


<!--点击修改按钮后跳转到修改页面，同时传入修改页面需要的信息-->
<script type="text/javascript">
    function delArticle(articleId) {
        if (confirm("是否确认删除该条数据？")) {
            if (!checkNotNull(articleId)) {
                // 使用zui组件中的漂浮消息js组件
                zuiMsg('系统错误，请刷新页面重试');
                return;
            }
            //    使用jquery提交删除请求，提交方式为post
            $.post("/songblog/article/del",
                //post的格式是url+{参数列表}，返回的data是结果数据
                {
                    articleId: articleId
                },
                function (data) {
                    if (data.code = 200) {
                        alert(data.message);
                        location.reload();//此语句为刷新当前页面
                    } else {
                        zuiMsg(data.message);
                    }
                    return;
                })
        }
    }


    function getNewData(pageNumber) {
        if (!checkNotNull(pageNumber)) {
            pageNumber = 1;
        }
        window.location.href = "/songblog/article/list?pageNumber=" + pageNumber + "<#if (articleTitle?? && articleTitle?length>0)>&articleTitle=${articleTitle}</#if>";
    }

    function renderPage() {
        let renderPageNumber = $("#renderPageNumber").val();
        if (!checkNotNull(renderPageNumber)) {
            new $.zui.Messager('请输入跳转的页码', {
                type: 'warning' // 定义颜色主题
            }).show();
            return;
        }
        let totalPage = '${articleVoIPage.totalPage}';
        if (parseInt(renderPageNumber) > parseInt(totalPage)) {
            renderPageNumber = totalPage;
        }
        getNewData(renderPageNumber);
    }
</script>
<#include "../import/bottom.ftl">