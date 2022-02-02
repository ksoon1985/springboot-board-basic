<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ksh.board.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/board2/js/boardScript.js"></script>
<script type="text/javascript" src="/resources/board2/js/jquery_board.js"></script><!-- jquery_board.js에 있는 쿼리 사용한다는 그런 뜻 -->
<link href="/resources/board2/css/color.css" rel="stylesheet">
</head>
<body>
	<table border=1>
		<thead class="class01">
			<tr>
				<th colspan=2>
					<h3>글 쓰 기</h3>
				</th>
			</tr>
		</thead>

		<tbody class="class02">
			<tr>
				<th>제목:</th>
				<td><c:out value="${bvo.subject}" /></td>
			</tr>
			<tr>
				<th>내용:</th>
				<td><textarea class="class03" >
						<c:out value="${bvo.content}" />
					</textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일:</th>
				<td><c:out value="${bvo.attachNm}" /></td>
			</tr>
			<tr>
				<th>작성자:</th>
				<td><c:out value="${bvo.writer}" /></td>
			</tr>
			<tr>
				<th>이 메 일:</th>
				<td><a href='mailto:<c:out value="${bvo.email}"/> '> 
				<c:out value="${bvo.email}" /></a></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="글수정" id="up_btn" onclick="pwCheck('u');" />
				<input type="button" value="글삭제" id="del_btn" onclick="pwCheck('d');"/>
					<input type="button" value="답글" id="reply" />
					<input type="button" value="글 목록으로...." id="list1" />
				</td>
			</tr>
		</tbody>
	</table> 
	
	<form  action="" name="parentform" method="post">
	<input type="hidden" name="currentPage" value='<c:out value="${pvo.currentPage}"/>'/>
	<input type="hidden" name="currPageBlock" value='<c:out value="${pvo.currPageBlock}"/>'/>
	<input type="hidden" name="cpass" value="">
	<input type="text" name="passwd" value="${bvo.passwd }">
	<input type="text" name="num" value="${bvo.num }">
	<input type="hidden" name="ref" value="${bvo.ref}">
	<input type="hidden" name="re_step" value="${bvo.re_step}">
	<input type="hidden" name="re_level" value="${bvo.re_level}">
	</form>
</body>
</html>