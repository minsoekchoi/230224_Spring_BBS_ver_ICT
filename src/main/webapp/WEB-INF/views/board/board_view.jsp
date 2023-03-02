<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
	div{ width:700px; margin: 0 auto; text-align: center;}
</style>
<script type="text/javascript">
	function list_go(f) {
		f.action="board_list.do";
		f.submit();
	}
	function ans_write(f) {
		f.action="board_ans_write.do";
		f.submit();
	}
	function update_go(f) {
		f.action="board_update.do";
		f.submit();
	}
	function delete_go(f) {
		f.action="board_delete.do";
		f.submit();
	}
</script>
</head>
<body>
<div>
 <h2> BOARD 상세보기 </h2>
	<form method="post">
	<table width="700px">
	<tbody>
	<tr>
		<th bgcolor="#B2EBF4">작성자</th>
		<td>${bovo.writer}</td>
	</tr>
	
	<tr>
		<th bgcolor="#B2EBF4">제목</th>
		<td> ${bovo.title }</td>
	</tr>
	<tr>
		<th bgcolor="#B2EBF4">날짜</th>
		<td>${bovo.regdate.substring(0,10) } </td>
	</tr>
	<tr>
		<th bgcolor="#B2EBF4">내용</th>
		<td style="text-align: left; padding: 20px;"><pre>${bovo.content}</pre></td>
	</tr>
	<tr>
		<th bgcolor="#B2EBF4">첨부</th>
		<td>
			<c:choose>
				<c:when test="${! empty bovo.f_name}">
					<a href="board_download.do?f_name=${bovo.f_name}"><img  style="width: 100px;" src="resources/upload/${bovo.f_name}"> </a>
				</c:when>
				<c:otherwise>
					<b> 첨부파일 없음</b>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	</tbody>
	<tfoot>
	<tr>
     <td colspan="2">
        <input type="button" value="목록" onclick="list_go(this.form)" />
        <input type="button" value="답글" onclick="ans_write(this.form)" />
        <input type="button" value="수정" onclick="update_go(this.form)" />
        <input type="button" value="삭제" onclick="delete_go(this.form)" />
	    <input type="hidden" name="cPage" value="${cPage}"/>
	    <input type="hidden" name="idx" value="${idx}"/>
	    <input type="hidden" name="pwd" value="${bovo.pwd}"/>
     </td>
	</tr>
	</tfoot>
	</table>
	</form>
	</div>
</body>
</html>