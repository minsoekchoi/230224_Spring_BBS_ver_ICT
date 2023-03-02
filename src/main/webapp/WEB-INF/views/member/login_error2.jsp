<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	onload = function() {
		var ck = confirm("로그인 실패\n회원가입창으로 이동하시겠습니까?");
		if(ck){
			location.href = "member_join_form.do";
		}else{
			location.href = "member_login_form.do";
		}
	}
</script>
</body>
</html>