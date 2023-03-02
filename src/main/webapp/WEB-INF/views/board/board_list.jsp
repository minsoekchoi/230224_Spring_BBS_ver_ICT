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
	text-align: center;
	padding: 4px 10px;
	background-color: #F6F6F6;
}

th {
	text-align: center;
	padding: 4px 10px;
	background-color: #B2CCFF;
}
a{ text-decoration: none;}
div{ width:700px; margin: 0 auto; text-align: center;}
</style>
<script type="text/javascript">
	function write_go() {
		location.href = "board_write.do";
	}
</script>
</head>
<body>
<div>
	<h2>BOARD 리스트</h2>
	<table width="700px">
		<thead>
			<tr>
				<th>번호</th>
				<th style="width: 30%">제목</th>
				<th>글쓴이</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty board_list}">
					<tr><td colspan="5"><h3> 원하시는 자료는 존재하지 않습니다.</h3></td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${board_list}" varStatus="vs">
						<tr>
							<td>${paging.totalRecord - ((paging.nowPage-1)*paging.numPerPage + vs.index)}</td>
							<td style="text-align: left; padding-left: 10px;" >
								<c:forEach begin="1" end="${k.step}">
									&nbsp;[Re]
								</c:forEach>
								<a href="board_view.do?idx=${k.idx}&cPage=${paging.nowPage}">${k.title}</a>
							</td>
							<td>${k.writer}</td>
							<td>${k.regdate.substring(0,10)}</td>
							<td>${k.hit}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
		<!-- 페이지기법 -->
			<tfoot>
				<tr>
					<td colspan="4">
						   <%-- 이전 --%>
						    <c:choose>
						    	<c:when test="${paging.beginBlock > paging.pagePerBlock }">
						    		<a href="board_list.do?cPage=${paging.beginBlock-paging.pagePerBlock}">이전으로</a>
						    	</c:when>
						    </c:choose>
						    
						    <!-- 블록안에 들어간 페이지번호들 -->
							<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}" step="1" var="k">
								<%-- 현재 페이지는 링크X, 나머지는 해당 페이지로 링크 처리 --%>
								<c:if test="${k==paging.nowPage}">
									${k}
								</c:if>
								<c:if test="${k!=paging.nowPage}">
									<a href="board_list.do?cPage=${k}">${k}</a>
								</c:if>
							</c:forEach>
							
							<!-- 다음 -->
							<c:choose>
						    	<c:when test="${paging.endBlock < paging.totalPage }">
						    		<a href="bbs_list.do?cPage=${paging.beginBlock+paging.pagePerBlock}">다음으로</a>
						    	</c:when>
						    </c:choose>
					</td>
					<td>
						<input type="button" value="글쓰기" onclick="write_go()" />
					</td>
				</tr>
			</tfoot>
	</table>
	</div>
</body>
</html>














