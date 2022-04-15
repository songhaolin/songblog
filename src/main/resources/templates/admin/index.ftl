<#include "../import/top.ftl">
<div class="panel col-xs-12"><!-- panel用来创建一个白板，col-xs-尺寸，用来调节白板框的大小，可以参看zui白板和光栅的组件-->
    <div class="panel-body"><!--panel-body给创建的白板增加内容，一个白板中可以有其他白板-->
        <div class="col-xs-6"><!-- 单独使用col-xs-尺寸，可以使相邻白板不紧贴着-->
            <div class="panel">
                <div class="panel-body">
                    <!--h5定义段落字体大小，<i class = "icon icon-desktop>表示插入名为icon-desk的图标
                    此图标直接来源于zui官网的icon组件，是字体的一种，比图片的加载速度快
                    ${osName!}表示从后端返回的model中，取出名为osName的属性，!表示可以为空，一般都加上-->
                    <h5><i class="icon icon-desktop"></i> 系统信息:${osName!}</h5>
                    <h5><i class="icon icon-server"></i> 服务器IP:${hostName!}</h5>
                </div>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="panel">
                <div class="panel-body">
                    <h5><i class="icon icon-th"></i> 文章总数:${articleCount!}</h5>
                    <h5><i class="icon icon-th-list"></i> 文章类型总数${articleTypeCount!}</h5>
                    <h5><i class="icon icon-stack"></i> 文章标签总数:${articleTagCount!}</h5>
                    <h5><i class="icon icon-user"></i> 用户总数:${userCount!}</h5>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../import/bottom.ftl">