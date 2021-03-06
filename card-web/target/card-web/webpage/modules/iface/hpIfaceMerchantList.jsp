<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>商户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>商户列表 </h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
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
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="hpIfaceMerchant" action="${ctx}/interface/hpIfaceMerchant/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>中文全称：</span>
				<form:input path="merName" htmlEscape="false" maxlength="100"  class=" form-control input-sm"/>
			<span>英文全称：</span>
				<form:input path="merEname" htmlEscape="false" maxlength="100"  class=" form-control input-sm"/>
			<span>中文简称：</span>
				<form:input path="merAbbrName" htmlEscape="false" maxlength="50"  class=" form-control input-sm"/>
			<span>英文简称：</span>
				<form:input path="merAbbrEname" htmlEscape="false" maxlength="50"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="interface:hpIfaceMerchant:add">
				<table:addRow url="${ctx}/interface/hpIfaceMerchant/addForm" title="商户"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<%--<shiro:hasPermission name="interface:hpIfaceMerchant:edit">
			    <table:editRow url="${ctx}/interface/hpIfaceMerchant/form" title="商户" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>--%>
			<shiro:hasPermission name="interface:hpIfaceMerchant:del">
				<table:delRow url="${ctx}/interface/hpIfaceMerchant/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="interface:hpIfaceMerchant:import">
				<table:importExcel url="${ctx}/interface/hpIfaceMerchant/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<%--
			<shiro:hasPermission name="interface:hpIfaceMerchant:export">
	       		<table:exportExcel url="${ctx}/interface/hpIfaceMerchant/export" label="导出数字证书"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission>
	       	--%>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th  class="sort-column mer_id">商户号</th>
				<th  class="sort-column mer_name">中文全称</th>
				<th  class="sort-column mer_ename">英文全称</th>
				<th  class="sort-column mer_abbr_name">中文简称</th>
				<th  class="sort-column mer_abbr_ename">英文简称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hpIfaceMerchant">
			<tr>
				<td> <input type="checkbox" id="${hpIfaceMerchant.merId}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看商户', '${ctx}/interface/hpIfaceMerchant/form?merId=${hpIfaceMerchant.merId}','800px', '500px')">
						${hpIfaceMerchant.merId}
				</a></td>
				<td >
					${hpIfaceMerchant.merName}
				</td>
				<td >
					${hpIfaceMerchant.merEname}
				</td>
				<td >
					${hpIfaceMerchant.merAbbrName}
				</td>
				<td >
					${hpIfaceMerchant.merAbbrEname}
				</td>
				<td>
					<shiro:hasPermission name="interface:hpIfaceMerchant:view">
						<a href="#" onclick="openDialogView('查看商户', '${ctx}/interface/hpIfaceMerchant/form?merId=${hpIfaceMerchant.merId}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="interface:hpIfaceMerchant:edit">
    					<a href="#" onclick="openDialog('修改商户', '${ctx}/interface/hpIfaceMerchant/form?merId=${hpIfaceMerchant.merId}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="interface:hpIfaceMerchant:del">
						<a href="${ctx}/interface/hpIfaceMerchant/delete?merId=${hpIfaceMerchant.merId}" onclick="return confirmx('确认要删除该商户吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i>删除</a>
					</shiro:hasPermission>
                    <shiro:hasPermission name="interface:hpIfaceMerchant:export">
                        <a href="${ctx}/interface/hpIfaceMerchant/export?merId=${hpIfaceMerchant.merId}" onclick="return confirmx('确认要导出数字证书吗？', this.href)"   class="btn btn-default btn-xs"><i class="fa fa-file-code-o"></i>导出数字证书</a>
                    </shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>