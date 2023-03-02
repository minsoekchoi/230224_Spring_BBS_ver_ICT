<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<style type="text/css">
*{ font-size: 15px;}
table {
	margin: 50px auto;
	width: 600;
	border-collapse: collapse;
	font-size: 8pt;
	border-color: navy;
}

table, th, td {
	border: 1px solid black;
	padding: 10px;
}
tfoot{
   text-align: center;
}	
</style>
<script type="text/javascript">
	function login_go(f) {
		f.action = "member_login.do" ;
		f.submit();
	}
	function join_go(f) {
		f.action = "member_join_form.do" ;
		f.submit();	
	}
</script>
</head>
<body>
<div id="mydiv">
	<form method="post">
		<table>
			<thead>
				<tr>
					<th colspan="2"><h2>LogIn</h2></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="m_id"></td>
				</tr>
				<tr>
					<th>패스워드</th>
					<td><input type="password" name="m_pw"></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<input type="button" value="로그인" onclick="login_go(this.form)">
						<input type="button" value="회원가입" onclick="join_go(this.form)">
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
	</div>
</body>
</html>