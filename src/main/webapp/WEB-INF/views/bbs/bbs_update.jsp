<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
#bbs table {
	width: 580px;
	margin-left: 10px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	font-size: 14px;
}

#bbs table caption {
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 10px;
}

#bbs table th {
	text-align: center;
	border: 1px solid black;
	padding: 4px 10px;
}

#bbs table td {
	text-align: left;
	border: 1px solid black;
	padding: 4px 10px;
}
.no {width: 15%}
.subject {	width: 30%}
.writer {	width: 20%}
.reg {	width: 20%}
.hit {	width: 15%}
.title {	background: lightsteelblue}
.odd {	background: silver}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
<script type="text/javascript">
function list_go(f) {
	f.action="bbs_list.do";
	f.submit();
}

function sendData(f) {
	var pwd = "${pwd}";
	if(f.pwd.value == pwd){
		f.action="bbs_delete_ok.do"
		f.submit();
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
	<div id="bbs">
		<form method="post">
			<table summary="게시판 수정">
				<caption>게시판 수정</caption>
				<tbody>
					<tr>
						<th>제목:</th>
						<td><input type="text" name="subject" size="45"
							value="${bvo.subject}" /></td>
					</tr>
					<tr>
						<th>이름:</th>
						<td><input type="text" name="writer" size="12"
							value="${bvo.writer}" /></td>
					</tr>
					<tr>
						<th>내용:</th>
						<td><textarea name="content" id="content" cols="50" rows="8">${bvo.content}</textarea></td>
					</tr>
					<tr>
						<th>첨부:</th>
						<td>
							<c:choose>
							<c:when test="${empty bvo.f_name }">
								<input type="file" name="f_param"> <b>이전 파일 없음</b>
								<input type="hidden" name="old_f_name" value="">
							</c:when>
							<c:otherwise>
								<input type="file" name="f_param"> <b>이전 파일명 (${bvo.f_name})</b>
								<input type="hidden" name="old_f_name" value="${bvo.f_name}">	
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr>
						<th>비밀번호:</th>
						<td><input type="password" name="pwd" size="12" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="hidden" name="cPage" value="${cPage}" > 
							<input type="hidden" name="b_idx" value="${b_idx}" > 
							<input type="button" value="수정 완료"	onclick="sendData(this.form)" > 
							<input type="reset" value="초기화" /> 
							<input type="button" value="목록" onclick="list_go(this.form)" >
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<script src="resources/js/summernote-lite.js"></script>
	<script src="resources/js/lang/summernote-ko-kr.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#content").summernote({
				lang : "ko-KR",
				height : 300,
				focus : true,
				callbacks : {
					onImageUpload : function(files, editor) {
						for (var i = 0; i < files.length; i++) {
							sendImage(files[i], editor)
						}
					}
				}
			});
		});
		
		function sendImage(file, editor) {
			var frm = new FormData(); 
			frm.append("upload", file);

			// 비동기 통신
			$.ajax({
				url : "${pageContext.request.contextPath}/view/saveImage.jsp", 
				data : frm, // 전달하고자 하는 파라미터 값
				type : "post", // 전송 방식
				contentType : false,
				processData : false,
				dataType : "json",
			}).done(function(data) {
				$("#content").summernote("editor.insertImage", data.img_url);
			});
		}
	</script>
</body>
</html>

