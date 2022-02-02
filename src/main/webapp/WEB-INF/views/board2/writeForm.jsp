<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% request.setCharacterEncoding("utf-8"); %> <!-- 한글 안 깨짐 -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page import="com.ksh.board.dto.*, java.util.*"%>
<!DOCTYPE html>
<html>	
<head>	
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>		
<script type="text/javascript" src="/resources/board2/js/boardScript.js"></script>
<script type="text/javascript" src="/resources/board2/js/jquery_board.js" ></script>
<link href="/css/board.css" rel="stylesheet">
</head>	
<body> <!-- useBean은 jsp용 -->
		
					<!-- jsp에서 do로 바꿔줘야됨          onsubmit="return sendData()"-->
	<form action="writePro.sp" method="post" name="writeForm" enctype="multipart/form-data">
			<input type="text" name="num" value="${bvo.num}">
			<input type="hidden" name="ref" value="${bvo.ref}">
			<input type="text" name="re_step" value="${bvo.re_step}">
			<input type="text" name="re_level" value="${bvo.re_level}">
			<input type="hidden" name="currentPage" 
			value='<c:out value="${pvo.currentPage}"/>'/>
			<input type="hidden" name="currPageBlock" 
			value='<c:out value="${pvo.currPageBlock}"/>'/>
		<table border=1>
			<thead>
				<tr>
					<th colspan=2>
						<h3>글쓰기</h3>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>제목:</th>
					<c:if test="${bvo.num==0}">
					<td><input type="text" size="80%" placeholder="제목을 입력하세요" name="subject"></td></c:if>
					<c:if test="${bvo.num!=0}">
					<td><input type="text" size="80%" placeholder="제목을 입력하세요" name="subject" value="[답글]"></td></c:if>
				</tr>
				<tr>
					<th>내용:</th>
					<td><textarea cols="100" rows="20" placeholder="내용을 입력하세요" name="content"></textarea></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<td><input type="file" placeholder="파일을 선택하세요." name="afile"></td>
				<tr>
				<tr>
					<th>작 성 자:</th>
					<td><input type="text" placeholder="작성자를 입력하세요" name="writer"></td>
				<tr>
				<tr>
					<th>이 메 일:</th>
					<td><input type="text" placeholder="메일 주소를 입력하세요" name="email" ></td>
				<tr>
				<tr>
					<th>비밀번호:</th>
					<td><input type="password" placeholder="비밀번호를 입력하세요" name="passwd"></td>
				<tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" name="submit" id="submit" value="등록" />
						<!-- <input type="button" value="답글" id="reply" /> -->
						<input type="button" name="listbtn" id="list2" value="글 목록으로...." />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>