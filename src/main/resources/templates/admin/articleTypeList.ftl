<!DOCTYPE html>
<#include "../import/adminTop.ftl">

<div class="panel">
    <div class="panel-body">
<#--        <button onclick="addOrUpdateUserPage()" type="button" class="btn btn-mini"><i class="icon icon-success"></i>添加文章标签</button>-->
        <button onclick="addOrUpdateUserPage()" class="btn btn-success " type="button">添加文章标签</button>
    </div>
</div>
<!--判断返回的数据是否为空且list的size属性是否大于0，满足条件则进入-->
<#if articleTypeVoList??&&articleTypeVoList?size gt 0>
<!--面板+表格-->
<div class="panel">
    <div class="panel-body">
        <table class="table">
            <thead>
            <tr><!--tr表示表头信息-->
                <th>排序</th>
                <th>文章类型名称</th>
                <th>文章数</th>
                <th>添加时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody><!--tbody表示表格填充信息,就是填充数据的位置-->
            <#list articleTypeVoList as articleType><!--将userpage以list的形式遍历，其中的每个元素以user的形式取出，需要与后端返回的对应-->
            <tr>
                <td>${articleType.articleTypeSort!}</td>
                <td>${articleType.articleTypeName!}</td>
                <td>${articleType.articleCount!}</td>
                <td>
                    <#if articleType.getArticleTypeAddTime()??>
                        ${(articleType.articleTypeAddTime)?string("yyyy-MM-dd HH:mm:ss")}
                    <#else >
                        -
                    </#if>
                </td>
                <td>
                    <!--设置操作按钮，button为按钮语法，onclick表示触发事件，type表示按钮类型，class设置按钮格式-->
                    <button onclick="addOrUpdateUserPage('${(articleType.articleTypeId)!}','${(articleType.articleTypeName)!}','${(articleType.articleTypeSort)!}')"
                            type="button" class="btn btn-mini"><i class="icon icon-cog"></i>修改
                    </button>
                    <button onclick="delUser('${(articleType.articleTypeId)!}')" type="button" class="btn btn-mini"><i
                                class="icon icon-remove"></i>删除
                    </button>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
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

<!--增加一个zui对话框组件，作为修改用户信息时的弹出页面-->
<div class="modal fade" id="articleTypeAddOrUpdateModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                                class="sr-only">关闭</span></button>
                    <h4 class="modal-title">修改文章类型</h4>
                </div>
                <div class="modal-body"><!--对话框中的内容是一个表单，用来展示用户信息以及提交修改后的信息-->
                    <!--修改表单页面要有userId信息，并处于隐藏状态，不对用户可见，type设置为hidden-->
                    <input type="hidden" name="articleTypeId" id="articleTypeId">
                    <!--表单中需要展示的字段，每个输入框要有单独的name和id，提交表单时要对应-->
                    <div class="form-group"><!--如果不允许修改输入框的值，不要name属性，同时添加disabled="disabled"属性-->
                        <label for="articleTypeNameUpdate">文章类型名称：</label>
                        <!--由于userName作为页面的变量名已经在列表查询的地方使用了，这里要定义新的变量名，以防冲突,for和id要统一-->
                        <input type="text" class="form-control" name="articleTypeNameUpdate" id="articleTypeNameUpdate"
                               placeholder="文章类型名称">
                    </div>
                    <div class="form-group">
                        <label for="articleTypeSort">文章类型排序：</label>
                        <input type="number" class="form-control" name="articleTypeSort" id="articleTypeSort"
                               placeholder="文章类型排序">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" onclick="addOrUpdateArticleTypeAction()" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--点击修改按钮后跳转到修改页面，同时传入修改页面需要的信息-->
<script type="text/javascript">
    //提交页面修改后的信息到后台
    function addOrUpdateArticleTypeAction() {
        //获取用户提交的修改页面的信息
        let articleTypeId = $("#articleTypeId").val();//通过id获取页面提交的字段值
        let articleTypeNameUpdate = $("#articleTypeNameUpdate").val();//通过id获取页面提交的字段值
        let articleTypeSort = $("#articleTypeSort").val();//通过id获取页面提交的字段值
        $.post('/songblog/article/type/addOrUpdate', {
                articleTypeId: articleTypeId,
                articleTypeName: articleTypeNameUpdate,
                articleTypeSort: articleTypeSort,
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

    //展示修改页面
    function addOrUpdateUserPage(articleTypeId, articleTypeName, articleTypeSort) {
        // 打开修改页面 使用的是zui的对话框组件--手动显示或隐藏对话框，#用来获取id的值，#userUpdateModal对应的是update页面的id，就是上面的update对话框的id
        $('#articleTypeAddOrUpdateModal').modal('toggle', 'center');
        //为页面上的字段赋值$("页面上字段的id").val(要赋的值)
        $("#articleTypeId").val(articleTypeId);
        $("#articleTypeNameUpdate").val(articleTypeName);
        $("#articleTypeSort").val(articleTypeSort);
    }

    function delUser(articleTypeId) {
        if (confirm("是否确认删除该条数据？")) {
            if (!checkNotNull(articleTypeId)) {
                // 使用zui组件中的漂浮消息js组件
                zuiMsg('系统错误，请刷新页面重试');
                return;
            }
            //    使用jquery提交删除请求，提交方式为post
            $.post("/songblog/article/type/del",
                //post的格式是url+{参数列表}，返回的data是结果数据
                {
                    articleTypeId: articleTypeId
                },
                function (data) {
                    if (data.code = 200) {
                        alert(data.message);
                        location.reload();//此语句为刷新当前页面
                    }else {
                        zuiMsg(data.message);
                    }
                    return;
                })
        }
    }
</script>
<#include "../import/bottom.ftl">