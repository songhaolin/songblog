<!DOCTYPE html>
<#include "../import/top.ftl">
<div class="panel" xmlns="http://www.w3.org/1999/html">
    <div class="panel-body">
        <!--form-horizontal类表示该form是一个表单，可参看zui”表单“ action规定该表单要提交的目标路径 method表示提交的方式-->
        <form class="form-horizontal" action="/songblog/user/list" method="get">
            <div class="form-group">
                <!--        lable表示标签，说明该输入框的含义-->
                <label for="userName" class="col-sm-1">用户名:</label>
                <!--div class="col-sm-2"表示该div内定义的内容尺寸为2个单位长度-->
                <div class="col-sm-2">
                    <!--input 表示该组件是个输入框，text表示输入内容是文本，class表示是表单控制
                    name和id表示该输入框对应的变量，与后端接收变量名一致，placeholder为默认展示值-->
                    <input type="text" class="form-control" name="userName" id="userName" placeholder="用户名">
                </div>
                <div class="col-sm-1">
                    <!--button表示该组件是一个按钮，type表示这个按钮是用来提交表单信息的-->
                    <button type="submit" class="btn btn-success">查询</button>
                </div>
                <div class="col-sm-1">
                    <!--button表示该组件是一个按钮，type表示这个按钮是用来提交表单信息的-->
                    <a href="/songblog/user/list" class="btn btn-success">查询全部</a>
                </div>
            </div>
        </form>
    </div>
</div>
<!--判断返回的用户数据（userPage）是否为空且list的size属性是否大于0，满足条件则进入-->
<#if userPage??&&userPage.list?size gt 0>
    <h4><i class="icon icon-info-sign"></i> 提示：被冻结的用户无法登录</h4>
    <!--面板+表格-->
    <div class="panel">
        <div class="panel-body">
            <table class="table">
                <thead>
                <tr><!--tr表示表头信息-->
                    <th>用户名</th>
                    <th>是否冻结</th>
                    <th>注册时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody><!--tbody表示表格填充信息,就是填充数据的位置-->
                <#list userPage.list as user><!--将userpage以list的形式遍历，其中的每个元素以user的形式取出，需要与后端返回的对应-->
                <tr>
                    <td>${user.userName!}</td>
                    <td>
                        <#if (user.userFrozen)??&&(user.userFrozen)==1>
                            <span class="label label-danger">冻结</span><!--使用zui标签组件，将输出字体美华-->
                        <#else >
                            <span class="label label-success">正常</span>
                        </#if>
                    </td>
                    <td>${(user.userRegisterTime)?string("yyyy-MM-dd HH:mm:ss")}</td><!--将时间转为字符串-->
                    <td>
                        <!--设置操作按钮，button为按钮语法，onclick表示触发事件，type表示按钮类型，class设置按钮格式-->
                        <button onclick="updateUser('${(user.userId)!}','${(user.userName)!}','${(user.userFrozen)!}')" type="button" class="btn btn-mini"><i class="icon icon-cog"></i>修改</button>
                        <button onclick="delUser('${(user.userId)!}')" type="button" class="btn btn-mini"><i
                                    class="icon icon-remove"></i>删除
                        </button>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
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
                    <#if userPage.pageNumber lte 1>
                        <li class="previous.disabled">
                            <a href="javascript:void(0)"><i class="icon icon-chevron-left"></i></a>
                        </li>
                    <#else>
                        <li class="previous" onclick="getNewData('${userPage.pageNumber-1}')">
                            <a href="javascript:void(0)"><i class="icon icon-chevron-left"></i></a>
                        </li>
                    </#if>
                    <!--处理当前页和总页展示-->
                    <li>
                        <a href="javascript:void(0)" class="btn">${userPage.pageNumber}
                            页/共${userPage.totalPage}页
                        </a>
                    </li>
                    <!--处理下一页功能-->
                    <#if userPage.pageNumber gte userPage.totalPage>
                        <li class="next disabled">
                            <a href="javascript:void(0)"><i class="icon icon-chevron-right"></i></a>
                        </li>
                    <#else >
                        <li class="next" onclick="getNewData('${userPage.pageNumber+1}')">
                            <a href="javascript:void(0)"><i class="icon icon-chevron-right"></i></a>
                        </li>
                    </#if>
                    <!--展示最后一页-->
                    <li class="next" onclick="getNewData('${userPage.totalPage}')">
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
<div class="modal fade" id="userUpdateModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/songblog/user/update">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                                class="sr-only">关闭</span></button>
                    <h4 class="modal-title">修改用户</h4>
                </div>
                <div class="modal-body"><!--对话框中的内容是一个表单，用来展示用户信息以及提交修改后的信息-->
                    <!--修改表单页面要有userId信息，并处于隐藏状态，不对用户可见，type设置为hidden-->
                    <input type="hidden" name="userId" id="userId">
                    <!--表单中需要展示的字段，每个输入框要有单独的name和id，提交表单时要对应-->
                    <div class="form-group"><!--如果不允许修改输入框的值，不要name属性，同时添加disabled="disabled"属性-->
                        <label for="userNameUpdate">用户账号：</label>
                        <!--由于userName作为页面的变量名已经在列表查询的地方使用了，这里要定义新的变量名，以防冲突,for和id要统一-->
                        <input type="text" class="form-control" disabled="disabled" id="userNameUpdate"
                               placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label for="userPassword">用户密码：</label>
                        <input type="password" class="form-control" name="userPassword" id="userPassword"
                               placeholder="用户密码">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputMoney1">是否冻结</label>
                        <div class="input-group">
                            <!--这里使用zui单选框给用户展示和修改-->
                            <label class="radio-inline">
                                <input type="radio" name="userFrozen" value="0" checked="checked">正常<!--checked="checked"表示默认情况是正常的-->
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="userFrozen" value="1">冻结
                            </label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" onclick="updateUserAction()" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--点击修改按钮后跳转到修改页面，同时传入修改页面需要的信息-->
<script type="text/javascript">

    //提交页面修改后的信息到后台
    function updateUserAction() {
        //获取用户提交的修改页面的信息
        let userId = $("#userId").val();//通过id获取页面提交的字段值
        let userName = $("#userNameUpdate").val();//通过id获取页面提交的字段值
        let userPassword = $("#userPassword").val();//通过id获取页面提交的字段值
        let userFrozen  =$("input[name='userFrozen']:checked").val();//获取选中的radio单选框对应的值
        console.log(userFrozen);
        if (!checkNotNull(userId)){
            zuiMsg("程序出错，请刷新页面重试");
        }
        if (!checkNotNull(userFrozen)){
            zuiMsg("请选择是否冻结用户");
        }
        $.post('/songblog/user/update',{
            userId:userId,
            userFrozen:userFrozen,
            userName:userName,
            userPassword:userPassword
        },
        function (data){
            if (data.code==200){
                alert(data.message);
                location.reload();
                return;
            }
            zuiMsg(data.message);
        }

        )

    }

    //展示修改页面
    function updateUser(userId, userName, userFrozen) {
    // 打开修改页面 使用的是zui的对话框组件--手动显示或隐藏对话框，#用来获取id的值，#userUpdateModal对应的是update页面的id，就是上面的update对话框的id
        $('#userUpdateModal').modal('toggle', 'center');
        console.log(userId);//可以直接在浏览器的调试控制栏中看到打印的信息
        //为页面上的字段赋值$("页面上字段的id").val(要赋的值)
        $("#userId").val(userId);
        $("#userNameUpdate").val(userName);
        //单选框的赋值是另一种方式，jquery的radio选择器
        $(":radio[name='userFrozen'][value='"+userFrozen+"']").prop("checked","checked");

    }

    function delUser(userId) {
        if (confirm("是否确认删除该条数据？")) {
            if (!checkNotNull(userId)) {
                // 使用zui组件中的漂浮消息js组件
                new $.zui.Messager('系统错误，请刷新页面重试', {
                    type: 'warning' // 定义颜色主题
                }).show();
                return;
            }
            //    使用jquery提交删除请求，提交方式为post
            $.post("/songblog/user/del",
                //post的格式是url+{参数列表}，返回的data是结果数据
                {
                    userId: userId
                },
                function (data) {
                    if (data.code = 200) {
                        alert(data.message);
                        location.reload();//此语句为刷新当前页面
                        return;
                    }
                    new $.zui.Messager(data.message, {
                        type: 'warning' // 定义颜色主题
                    }).show();
                })
        }
    }

    function getNewData(pageNumber) {
        if (!checkNotNull(pageNumber)) {
            pageNumber = 1;
        }
        window.location.href = "/songblog/user/list?pageNumber=" + pageNumber + "<#if (userName?? && userName?length>0)>&userName=${userName}</#if>";
    }

    function renderPage() {
        let renderPageNumber = $("#renderPageNumber").val();
        if (!checkNotNull(renderPageNumber)) {
            new $.zui.Messager('请输入跳转的页码', {
                type: 'warning' // 定义颜色主题
            }).show();
            return;
        }
        let totalPage = '${userPage.totalPage}';
        if (parseInt(renderPageNumber) > parseInt(totalPage)) {
            renderPageNumber = totalPage;
        }
        getNewData(renderPageNumber);
    }


</script>
<#include "../import/bottom.ftl">