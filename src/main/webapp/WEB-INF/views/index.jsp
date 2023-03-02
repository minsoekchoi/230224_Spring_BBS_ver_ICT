<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫화면</title>
<style type="text/css">
	ul.menu{
	  list-style-type: none;
	  margin: auto;
	  padding: 0px;	
	}
	ul.menu a{
	    background-color: tomato;
	    color: white;
	    text-decoration: none;
	    float: left;
	    padding: 20px 40px;
	    border-right: 3px solid white;
	}
	ul.menu a:hover{
		background-color: skyblue;
		font-weight: bold;
	}
	header{text-align: center;}
	nav{margin-top: 25px; margin-left: 550px;}
</style>
</head>
<body>
	<header>
		<h2> 한국 ICT 인재 개발원 </h2>
		<nav>
			<ul class="menu">
				<li><a href="bbs_list.do"> BBS </a></li>
				<li><a href="board_list.do"> BOARD </a></li>
				<li><a href="shop_list.do"> SHOP </a></li>
				<li><a href="member_login_form.do"> Login </a></li>
			</ul>
		</nav>
	</header>
</body>
</html>