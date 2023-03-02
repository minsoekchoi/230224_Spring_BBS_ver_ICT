<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a { text-decoration: none;}
	table{width: 600px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 600px; margin:auto; text-align: center;}
</style>
<script type="text/javascript">
	function list_go(f) {
		f.action="board_list.do";
		f.submit();
	}
	
	function delete_ok(f) {
		// 비밀번호 
		var k = "${pwd}";
		if(f.pwd.value == k){
			var chk = confirm("정말 삭제할까요?");
			if(chk){
				f.action="board_delete_ok.do"
				f.submit();
			}else{
				history.go(-1);
			}
		}else{
			alert("비밀번호 틀림");
			f.pwd.value = "";
			f.pwd.focus();
			return;
		}
	}
</script>
</head>
<body>
	<div>
		<h2> BOARD : 삭제화면</h2>
		<form method="post">
			<table>
				<tbody>
					<tr>
						<th style="background-color: #B2CCFF;"> 비밀번호 </th>
						<td> <input type="password" name="pwd"> </td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="button" value="삭제" onclick="delete_ok(this.form)">
							<input type="button" value="목록" onclick="list_go(this.form)">
							<input type="hidden" name="idx" value="${idx}">
							<input type="hidden" name="cPage" value="${cPage}">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>