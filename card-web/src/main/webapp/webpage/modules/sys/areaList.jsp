<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>区域管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/webpage/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)};
            $("#treeTableList").append(Mustache.render(tpl,{"list":data}));
			//addRow("#treeTableList", tpl, data, rootId, true);
			//$("#treeTable").treeTable({expandLevel : 5});
			$('#treeTable').treeTable({
				            expandLevel :2,
			               beforeExpand : function($treeTable,id) {
                               $.ajax({
                                   type: 'POST',
                                   url: "${ctx}/sys/area/ajaxList",
                                   data: {"parentId":id} ,
                                   success: function(list){
                                       if ($('.' + id, $treeTable).length) { return; }
                                       $treeTable.addChilds(Mustache.render(tpl,{"list":list}));
                                   } ,
                                   dataType: "json"
                               });
				           }
			         });

		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							type: getDictLabel(${fns:toJson(fns:getDictList('sys_area_type'))}, row.type)
						}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}

		function refresh(){//刷新
			
			window.location="${ctx}/sys/area/";
		}
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
			<h5>区域列表 </h5>
			<div class="ibox-tools">
				<a class="collapse-link">
					<i class="fa fa-chevron-up"></i>
				</a>
				<a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
					<i class="fa fa-wrench"></i>
				</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#">选项1</a>
					</li>
					<li><a href="#">选项2</a>
					</li>
				</ul>
				<a class="close-link">
					<i class="fa fa-times"></i>
				</a>
			</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sys:area:add">
				<table:addRow url="${ctx}/sys/area/form" title="区域"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:area:import">
				<table:importExcel url="${ctx}/sys/area/import"></table:importExcel><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:area:export">
				<table:exportExcel url="${ctx}/sys/area/export"></table:exportExcel><!-- 增加按钮 -->
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
		</div>
	</div>
	</div>
	
	<table id="treeTable"  class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead><tr><th>区域名称</th><th>区域编码</th><th>区域类型</th><th>备注</th><th>操作</th></tr></thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<br/>
	</div>
	</div>
</div>
	<script type="text/template" id="treeTableTpl">
        {{#list}}
		<tr id="{{id}}" pId="{{parentId}}" {{#isParent}} hasChild="true" {{/isParent}}>
			<td><a  href="#" onclick="openDialogView('查看区域', '${ctx}/sys/area/form?id={{id}}','800px', '500px')">{{name}}</a></td>
			<td>{{code}}</td>
			<td>{{typeStr}}</td>
			<td>{{remarks}}</td>
			<td>
				<shiro:hasPermission name="sys:area:view">
				<a href="#" onclick="openDialogView('查看区域', '${ctx}/sys/area/form?id={{id}}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i>  查看</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:area:edit">
					<a href="#" onclick="openDialog('修改区域', '${ctx}/sys/area/form?id={{id}}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:area:del">
					<a href="${ctx}/sys/area/delete?id={{id}}" onclick="return confirmx('要删除该区域及所有子区域项吗？', this.href)" class="btn btn-danger btn-xs" ><i class="fa fa-trash"></i> 删除</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:area:add">
					<a href="#" onclick="openDialog('添加下级区域', '${ctx}/sys/area/form?parent.id={{id}}','800px', '500px')" class="btn btn-primary btn-xs" ><i class="fa fa-plus"></i> 添加下级区域</a>
				</shiro:hasPermission>
			</td>
		</tr>
        {{/list}}
	</script>
</body>
</html>