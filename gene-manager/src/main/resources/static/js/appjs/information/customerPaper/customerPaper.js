
var prefix = "/information/customerPaper"
$(function() {
	load();
//	load2();
});

/*function load2() {
    $('#exampleTable').bootstrapTable({
        ajax: function (request) {
            $.ajax({
                type: "GET",
                url: "/information/customerPaper/userList",
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                json: 'callback',
                success: function (json) {
                	console.info(json);
                    var dynamicHeader = [];
                    dynamicHeader.push({
                        field: "state",
                        check: true
                    });

                    for (var i = 0; i<(Object.keys(json[0])).length; i++) {
                        var property = (Object.keys(json[0]))[i];
                       // console.log(property);
                        dynamicHeader.push({
                            "title": property,
                            "field": property,
                            switchable: true,
                            sortable: false
                        });
                    }
                     dynamicHeader.push({
                        title : '操作',
    					field : 'id',
    					align : 'center',
    					formatter : function(value, row, index) {
    						var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
    								+ row.userId
    								+ '\')"><i class="fa fa-edit"></i></a> ';
    						var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
    								+ row.userId
    								+ '\')"><i class="fa fa-remove"></i></a> ';
    						var f = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="details(\''
    								+ row.userId
    								+ '\')"><i class="fa fa-key"></i></a> ';
    						return d + f;
    					}
                      });
                    
                    
                    //console.log(Object.keys(json[0]));

                    $('#exampleTable').bootstrapTable('destroy').bootstrapTable({
                        data: json,
                        toolbar: '#toolbar',
                        cache: false,
                        striped: true,
                        sidePagination: "client",
                        sortOrder: "desc",
                        pageSize: 25,
                        pageNumber: 1,
                        pageList: "[25, 50, 100]",
                        showToggle: false,
                        showColumns: false,
                        showExport: true,
                        exportDataType: "basic",
                        pagination: true,
                        strictSearch: false,
                        search: true,
                        columns: dynamicHeader
                    });
                },
                error: function () {
                    alert("SQL查询错误，请输入正确的SQL语句！");
                    location.reload();
                }
            });
        }
    });
}
*/

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
								startTime:$('#startTime').val(),
								endTime:$('#endTime').val(),
					            username:$('#username').val(),
					            productId:$("#productId").val(),
					            phone:$("#phone").val()
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
									field : 'username', 
									title : '姓名' 
								},
																{
									field : 'high', 
									title : '身高' 
								},
																{
									field : 'sex', 
									title : '性别' 
								},
																{
									field : 'weight', 
									title : '体重' 
								},
																{
									field : 'age', 
									title : '年龄' 
								},
																{
									field : 'answerTime', 
									title : '检测时间' 
								},
																{
									field : 'productName', 
									title : '产品名称' 
								},
																{
									field : 'phone', 
									title : '联系方式' 
								},
																/*{
									field : 'email', 
									title : '邮箱' 
								},*/
																/*{
									field : 'address', 
									title : '地址' 
								},*/
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										/*var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="答题详情"  mce_href="#" onclick="details(\''
												+ row.productPaperId
												+ '\')"><i class="fa fa-key"></i></a> ';
										var g = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="PDF导出" onclick="edit(\''
										+ row.id
										+ '\')"><i class="fa fa-edit"></i></a> ';
										*/
										
										

										var f = '<a class="btn btn-primary btn-xs" href="#" title="答题详情" onclick="details(\''
												+ row.productPaperId
												+ '\')" style="text-decoration: none;">答题性情</a> ';
										var g = '<a class="btn btn-success btn-xs" href="#" title="PDF导出" onclick="pdfexport(\''
												+ row.productPaperId
												+ '\',\''+row.productId+'\',\''+row.productName+'\')" style="text-decoration: none;">PDF导出</a> ';
										return  f+g;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '320px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function details(id) {
	var detailsPage = layer.open({
		type : 2,
		title : '详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/datixiangqing/' + id // iframe的url
	});
	layer.full(detailsPage);
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

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}

function pdfexport(productPaperId,product,name){
	
	window.location.href=prefix+"/exportPDF?productpaper="+productPaperId+"&product="+product+"&name="+name
}