
var prefix = "/information/questionnaire"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								code:$("#code").val(),
								status:$("#status option:selected").val()
							
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
																{
									field : 'code', 
									title : '问卷编号' 
								},
																{
									field : 'name', 
									title : '问卷名称'
								},
																{
									field : 'description', 
									title : '问卷简介' 
								},
															 	{
									field : 'createTime', 
									title : '创建时间' 
								},
																{
									field : 'status', 
									title : '问卷状态',
									formatter : function(value, row, index) {
										if(value==0) return "待发布";
										if(value==1) return "已发布";
										if(value==2) return "已停用";
									}
								},
																
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										
										var e = '<a class="btn btn-primary btn-xs" href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')" style="text-decoration: none;">编辑</a> ';
										var d = '<a class="btn btn-warning btn-xs" href="#" title="删除" onclick="remove(\''
												+ row.id
												+ '\')" style="text-decoration: none;">删除</a> ';
										var f = '<a class="btn btn-success btn-xs" href="#" title="发布" onclick="edit2(\''
												+ row.id
												+ '\',\''+1+'\')" style="text-decoration: none;">发布</a>';
										var g = '<a class="btn btn-primary btn-xs" href="#" title="停用" onclick="edit2(\''
												+ row.id
												+ '\',\''+2+'\')" style="text-decoration: none;">停用</a>';
										
										if(row.status==0){
											return e+d+f;
										}
										if(row.status==1){
											return g;
										}
										if(row.status==2){
											return e+d+f;
										}
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	var page=layer.open({
		type : 2,
		title : '添加问卷',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
	layer.full(page);
}
function edit(id) {
	var page=layer.open({
		type : 2,
		title : '编辑问卷',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
	layer.full(page);
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function edit2(id,flag) {
	
		$.ajax({
			url : prefix+"/edit2",
			type : "post",
			data : {
				'id' : id,
				'flag':flag
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});

}
