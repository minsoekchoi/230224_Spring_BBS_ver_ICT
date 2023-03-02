<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#bbs table {
	    width:580px;
	    margin:0 auto;
	    margin-top:20px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th {
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	#bbs table td {
	    text-align:left;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightsteelblue}
	.odd {background:silver}
</style>
<script type="text/javascript">
	function update_go(f) {
		f.action="bbs_update.do";
		f.submit();
	}
	function delete_go(f) {
		f.action="bbs_delete.do";
		f.submit();
	}
	function list_go(f) {
		f.action="bbs_list.do";
		f.submit();
	}
</script>
</head>
<body>
	<div id="bbs" >
	<form method="post" >
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td>${bvo.subject}</td>
				</tr>
				<tr>
					<th>이름:</th>
					<td>${bvo.writer}</td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><pre>${bvo.content}</pre></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<td>
						<c:choose>
							<c:when test="${! empty bvo.f_name }">
								<a href="bbs_download.do?f_name=${bvo.f_name}"><img src="resources/upload/${bvo.f_name }"> </a>
							</c:when>
							<c:otherwise>
								 <b> 첨부파일 없음 </b>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="수정" onclick="update_go(this.form)">
						<input type="button" value="삭제" onclick="delete_go(this.form)">
						<input type="button" value="목록" onclick="list_go(this.form)">
						<input type="hidden" name="cPage" value="${cPage}">
						<input type="hidden" name="b_idx" value="${bvo.b_idx}">
						<input type="hidden" name="pwd" value="${bvo.pwd}">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	<br>
	<hr>
	<br>
	<div style="border: 1px solid blue; width: 60%; margin: 10px; padding: 5px 50px">
		<form method="post" action="com_write.do">
			<p> 이름 : <input type="text" name="writer" size="15"></p>
			<p> 내용 : <br> 
			    <textarea rows="4" cols="50" name="content"></textarea>
			</p>
			<p> 비밀번호 : <input type="password" name="pwd" size="15"> </p>
			<input type="hidden" name="b_idx" value="${bvo.b_idx}">
			<input type="hidden" name="cPage" value="${cPage}">
			<input type="submit" value="댓글저장">
		</form>
	</div>
	<br>
	<hr>
	<br>
	<%-- 댓글 출력 --%>	
	<div style="display: table; margin-left: 30px; ">
		<c:forEach var="k" items="${c_list}">
			<div style="border: 1px solid #cc00cc; width: 400px; padding: 0px 50px;">
				<form action="com_del.do" method="post">
					<p> 이름 : ${k.writer} </p>
					<p> 날짜 : ${k.write_date.substring(0,10)}</p>
					<p> 내용 : <pre>${k.content} </pre></p>
					<input type="hidden" name="c_idx" value="${k.c_idx }">
					<input type="hidden" name="b_idx" value="${k.b_idx }">
					<input type="hidden" name="cPage" value="${cPage}">
					<input type="submit" value="댓글 삭제">
				</form>
			</div>
			<hr>
		</c:forEach>
	</div>
</body>
</html>

