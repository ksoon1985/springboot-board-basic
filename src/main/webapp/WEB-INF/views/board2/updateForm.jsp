<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ksh.board.dto.*, java.util.*"%>
 <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/board2/js/boardScript.js"></script>
<script type="text/javascript" src="/resources/board2/js/jquery_board.js"></script>
<link href="/resources/board2/css/board.css" rel="stylesheet">
</head>
<body>
				<!-- updateProAction 으로 넘어감 -->
	<form action="updatePro.sp" method="post" name="updateForm" enctype="multipart/form-data">
		<table border=1>
			<thead>
				<tr>
					<th colspan=2>
						<h3>글수정</h3>
					</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<th>제목:</th>
					<td><input type="text" size="100%" placeholder="제목을 입력하세요" name="subject" 
					value='<c:out value="${bvo.subject}"/>'/>
					</td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><textarea cols="100" rows="20" placeholder="내용을 입력하세요" name="content">
					<c:out value="${bvo.content}"/>
					</textarea></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<td><input type="file" placeholder="파일을 입력하세요" name="afile"
					value='<c:out value="${bvo.attachNm}"/>'/><c:out value="${bvo.attachNm}"/></td>
				<tr>
				<tr>
					<th>작 성 자:</th>
					<td><input type="text" name="writer" value='<c:out value="${bvo.writer}"/>'/></td>
				<tr>
				<tr>
					<th>Email:</th>
					<td><input type="text" name="email" value='<c:out value="${bvo.email}"/>'/>
					</td>
				<tr>
				<tr>
					<th>비밀번호:</th>
					<td><input type="password" placeholder="비밀번호를 입력하세요" name="passwd"
						 value='<c:out value="${bvo.passwd}"/>'>
						 <input type="hidden" name="num" value='<c:out value="${bvo.num}"/>'/>	 
						 <input type="hidden" name="currentPage" value='<c:out value="${pvo.currentPage}"/>'/>
						 <input type="hidden" name="currentPageBlock" value='<c:out value="${pvo.currPageBlock}"/>'/>
					</td>
				<tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글수정" />
						<input type="button" value="글 목록으로...." id="list3" />
					</td>
				</tr>
				
			</tbody>
		</table>
	</form>
</body>
</html>