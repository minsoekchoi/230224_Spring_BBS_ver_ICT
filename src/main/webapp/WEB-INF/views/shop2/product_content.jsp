<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin: 10px auto;
	width: 1000px;
	border-collapse: collapse;
	font-size: 15px;
	border-color: navy;
}

table, th, td {
	border: 1px solid black;
	padding:10px;
}
</style>
<script type="text/javascript">
	function add_cart() {
		location.href ="shop2_addcart.do?idx=${s2vo.idx}";
	}
	function show_cart() {
		location.href ="shop2_showcart.do";
	}
</script>
</head>
<body>
	<jsp:include page="top.jsp" />
	<table>
		<tr>
			<td width="40%">제품Category</td>
			<td width="60%">${s2vo.category }</td>
		</tr>
		<tr>
			<td width="40%">제품번호</td>
			<td width="60%">${s2vo.p_num }</td>
			
		</tr>
		<tr>
			<td width="40%">제품이름</td>
			<td width="60%">${s2vo.p_name }</td>
		</tr>
		<tr>
			<td width="40%">제품판매사</td>
			<td width="60%">${s2vo.p_company }</td>
		</tr>
		<tr>
			<td width="40%">제품가격</td>
			<td width="60%">시중가 : <fmt:formatNumber value="${s2vo.p_price}" pattern="#,##0" />원 
			<font color="red">(할인가:<fmt:formatNumber value="${s2vo.p_saleprice}" pattern="#,##0" />원)</font></td>
		</tr>
		<tr>
			<td colspan="2" style="height: 90px;">${s2vo.p_content }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<img src="resources/images/${s2vo.p_image_l }" >
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button onclick="add_cart()"> 장바구니 담기 </button>
				<button onclick="show_cart()"> 장바구니 보기 </button>
			</td>
		</tr>
	</table>
</body>
</html>