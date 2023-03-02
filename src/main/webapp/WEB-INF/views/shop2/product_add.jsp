<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 물픔 등록 페이지 </title>
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
</head>
<body>
	<jsp:include page="top.jsp" />
	<div>
		<form action="shop2_product_ins_ok.do" method="post" enctype="multipart/form-data">
			<table>
				<thead>
					<tr>
						<th colspan="2"><h2> 물품 등록 페이지 (관리자 모드) </h2></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th> 분류 </th>
						<td>
							<input type="radio" name="category" value="com001"> 컴퓨터 
							<input type="radio" name="category" value="ele002" checked> 가전제품 
							<input type="radio" name="category" value="sp003"> 스포츠
						</td>
					</tr>
					<tr>
						<th>제품번호</th>
						<td><input type="text" name="p_num" size="15" required> </td>
					</tr>
					<tr>
						<th>제품명</th>
						<td><input type="text" name="p_name" size="15" required> </td>
					</tr>
					<tr>
						<th>판매사</th>
						<td><input type="text" name="p_company" size="15" required> </td>
					</tr>
					<tr>
						<th>상품가격</th>
						<td><input type="number" name="p_price" size="15" required> </td>
					</tr>
					<tr>
						<th>할인가격</th>
						<td><input type="number" name="p_saleprice" size="15" required> </td>
					</tr>
					<tr>
						<th>상품이미지(S)</th>
						<td><input type="file" name="s_image_s" size="15" required> </td>
					</tr>
					<tr>
						<th>상품이미지(L)</th>
						<td><input type="file" name="s_image_l" size="15" required> </td>
					</tr>
					<tr>
						<th colspan="2">상품내용</th>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="10" cols="100" name="p_content"></textarea></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2" style="text-align: center;">
							<input type="submit" value="상품등록">
							<input type="reset" value="취소">
						</td>
					</tr>
				</tfoot>
				
			</table>
		</form>
	</div>
</body>
</html>