<#include "../import/adminTop.ftl">
<div class="panel col-xs-12">
    <div class="panel-body">
        <input  style="width: 126px;" type="text" id="articleTagAddName" name="articleTagAddName" placeholder="添加文章标签"/>        <i class="icon icon-check" data-toggle="tooltip" data-placement="bottom" title="添加" onclick="articleTagAddAction()"></i>
        <hr/><!--这是一个分割线，防止button按钮和下面的内容重合-->
        <#if articleTagList?? && articleTagList?size gt 0>
            <#list articleTagList as articleTag>
                <div class="col-sm-2 " style="padding: 2px"><!--padding表示内边距-->
                    <div  style="width: 100%;height: 100%" class="img-thumbnail">
                        <!--因为这里是一个list循环，所以给输入框设置id和name的时候需要用articleTag.articleTagId,不能设置一个全局变量名，否则后面获取每个标签的输入框的值的时候会混乱-->
                        <input type="text" style="width: 70%" value="${(articleTag.articleTagName)!}"
                               id="${(articleTag.articleTagId)!}" name="${(articleTag.articleTagId)!}">
                        <div class="pull-right" style="margin-right: 10px">
                            <!--class="pull-right"表示右对齐,style="margin-right: 10px"表示右边内距为10px-->
                            <!--data-toggle="tooltip" data-placement="bottom" title="下方提示内容" 这个是zui组件提示消息，将鼠标放上去是会提示消息框-->
                            <i class="icon icon-cog" data-toggle="tooltip" data-placement="bottom" title="修改"
                               onclick="articleTagUpdateAction('${(articleTag.articleTagId)}')"></i>
                            <i class="icon icon-remove" data-toggle="tooltip" data-placement="bottom" title="删除"
                               onclick="articleTagDelAction('${(articleTag.articleTagId)}')"></i>
                        </div>
                    </div>
                </div>
            </#list>
        <#else >
            <div class="panel">
                <div class="panel-body">
                    <div style="text-align: center"><!--div style表示设置组件的格式，text-align设置文本对齐方式，center为居中-->
                        <h3><i class="icon icon-coffee"></i></h3>
                        <h3>暂无数据</h3>
                    </div>
                </div>
            </div>
        </#if>
    </div>
</div>


<script>

    function articleTagAddAction() {
        let articleTagAddName = $("#articleTagAddName").val();
        if (!checkNotNull(articleTagAddName)) {
            zuiMsg("标签名为空");
            return;
        }
        $.post('/songblog/article/tag/addOrUpdate', {
                articleTagName: articleTagAddName
            },
            function (data) {
                if (data.code = 200) {
                    alert(data.message);
                    location.reload();
                }else {
                    zuiMsg(data.message);
                }
                return;
            }
        )

    }


    function articleTagDelAction(articleTagId) {
        if (!checkNotNull(articleTagId)) {
            zuiMsg("系统错误，请刷新页面重试");
        }
        $.post('/songblog/article/tag/del', {
                articleTagId: articleTagId
            },
            function (data) {
                if (data.code == 200) {
                    alert(data.message);
                    location.reload();
                }else {
                    zuiMsg(data.message);
                }
                return;
            }
        )
    }


    function articleTagUpdateAction(articleTagId) {
        if (!checkNotNull(articleTagId)) {
            zuiMsg("系统错误，请刷新页面重试");
            return;
        }
        let articleTagName = $("#" + articleTagId).val();//根据每个标签的id作为输入框对应的id名，取出该输入框的值
        if (!checkNotNull(articleTagName)) {
            zuiMsg("标签名为空");
            return;
        }
        $.post('/songblog/article/tag/addOrUpdate', {
                articleTagName: articleTagName,
                articleTagId: articleTagId
            },
            function (data) {
                if (data.code == 200) {
                    alert(data.message);
                    location.reload();
                }else {
                    zuiMsg(data.message);
                }
                return;
            }
        )
    }

    // 这个是提示消息的初始化
    $('[data-toggle="tooltip"]').tooltip({
        placement: 'bottom'
    });

</script>
<#include "../import/bottom.ftl">
