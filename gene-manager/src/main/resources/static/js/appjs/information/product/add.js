$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/information/product/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			code : {
				required : true
			},
			paperId : {
				required : true
			},
			/*name : {
				required : true
			},
			description : {
				required : true
			},
			paperId : {
				required : true
			},*/
		},
		messages : {
			code : {
				required : icon + "请输入编号"
			},
			paperId : {
				required : icon + "问卷不能为空"
			},
			/*name : {
				required : icon + "请输入名称"
			},
			description : {
				required : icon + "请输入简介"
			},
			paperId : {
				required : icon + "请选择问卷"
			},*/
		}
	})
}