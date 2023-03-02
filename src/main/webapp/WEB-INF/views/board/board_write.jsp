<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
table {width: 1000px; margin: auto;}
h2 {margin: 20px auto; text-align: center;}
tr {
	    text-align:center;
	    padding:4px 10px;
	    background-color: #F6F6F6;
	}
	
th {
		width:120px;
	    text-align:center;
	    padding:4px 10px;
	    background-color: #B2CCFF;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
<script type="text/javascript">
	function list_go(f) {
		f.action="board_list.do";
		f.submit();
	}
	function write_ok_go(f) {
	  	if(f.writer.value.trim().length <= 0){
	  		alert("작성자를 입력하세요");
	  		f.writer.focus();
	  		return;
	  	}
	  	f.action = "board_write_ok.do";
	  	f.submit();
	}
</script>
</head>
<body>
	<div>
	<h2 class="center"> BOARD 글쓰기 </h2>
	<form method="post" enctype="multipart/form-data">
		<table class="center">
		<tbody>
			<tr>
				<th>작성자</th>
				<td align="left"><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td align="left"> <input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td align="left"><textarea rows="10" cols="60" name="content" id="content"></textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td align="left"><input type="file" name="f_param"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td align="left"><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="button" value="입력" onclick="write_ok_go(this.form)" > 
				<input type="button" value="목록" onclick="list_go(this.form)" > 
				<input type="reset" value="취소" />
				<input type="hidden" name="idx" value="${idx}">
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
				minHeight:400,
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
















