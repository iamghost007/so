/**
 * 为table指定行添加一行
 * 
 * tab 表id row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行 trHtml 添加行的html代码
 * 
 */
function addTr(tab, row, trHtml) {
	// 获取table最后一行 $("#tab tr:last")
	// 获取table第一行 $("#tab tr").eq(0)
	// 获取table倒数第二行 $("#tab tr").eq(-2)
	var $tr = $("#" + tab + " tr").eq(row);
	if ($tr.size() == 0) {
		alert("指定的table id或行数不存在！");
		return;
	}
	$tr.after(trHtml);
}

function delTr(ckb) {
	// 获取选中的复选框，然后循环遍历删除
	var ckbs = $("input[name=" + ckb + "]:checked");
	if (ckbs.size() == 0) {
		alert("要删除指定行，需选中要删除的行！");
		return;
	}
	ckbs.each(function() {
		$(this).parent().parent().remove();
	});
}
