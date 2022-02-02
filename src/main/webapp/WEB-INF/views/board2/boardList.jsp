<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ksh.board.dto.*, java.util.*"%>
<%
	request.setCharacterEncoding("utf-8"); /* web.xml에 한글깨짐 설정해서 주석 처리해도 안 깨짐 */
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
<link href="/resources/board2/css/board.css" rel="stylesheet">
<script type="text/javascript">
	function callContent(num) {
		//alert(num)
		document.content.num.value = num;
		document.content.submit();
	}
</script>
</head>
<body>
	<!-- 글목록을 출력 -->
 
	<h3 style="text-align:center;" id="head">게시글 목록</h3>
	<c:choose>
		<c:when test="${list.size()==0}">
			<table border="1">
				<tbody>
					<tr>
						<th><h3>게시글이 없습니다.</h3></th>
					</tr>
				</tbody>
			</table>
		</c:when>
		
		<c:when test="${list.size()!=0}">
			<table border="1">
				<thead class="class01">
					<tr>
						<th colspan="5"><h4 style="text-align: right;">
								전체 글 개수:&nbsp;
								<c:out value="${pvo.allCount}" />
								&nbsp;건
							</h4></th>
					</tr>
					<tr>
						<th>글번호</th>
						<th>글제목</th>
						<th>작성자</th>
						<th>작성일자</th>
						<th>조회수</th>
					</tr>
				<tbody class="class02">

					<!-- 해당 리스트를 출력 jstl 사용 -->
					<c:forEach var="dto" items="${list}">
						<tr>
							<td><c:out value="${dto.rr}" /> <input type="hidden"
								value='<c:out value="${dto.num}"/>' /></td>
							<c:if test="${dto.re_level==1}">
								<td align="Left"><a href="#"
									onclick="javascript:callContent('<c:out value="${dto.num}"/>');">
										<c:out value="${dto.subject}" />
								</a></td>
							</c:if>
							<c:if test="${dto.re_level>1}">
								<td align="Left"><img
									src="/resources/board2/images/level.gif"
									width="${dto.re_level*5}"> <img
									src="/resources/board2/images/re.gif"> <a href="#"
									onclick="javascript:callContent('<c:out value="${dto.num}"/>');">
										<c:out value="${dto.subject}" />
								</a></td>
							</c:if>
							<td><a href='mailto:<c:out value="${dto.email}"/>'> <c:out
										value="${dto.writer}" />
							</a></td>
							<td><c:out value="${dto.reg_date}" /></td>
							<td><c:out value="${dto.readcnt}" /></td>
						</tr>

					</c:forEach>					
					<tr>
						<td colspan="5" align="right"><a href="writeForm.sp"> <input
								type="button" value="글쓰기">
						</a></td>
					</tr>
				</tbody>
			</table>
			<h5 style="text-align: center">
				<c:if test="${pvo.startPage > pvo.pageBlock}">
					<a
						href="/boardList.sp?currentPage=${pvo.startPage - pvo.pageBlock}&currPageBlock=${pvo.currPageBlock - 1}"
						style="text-decoration: none;">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${pvo.startPage}" end="${pvo.endPage}">
					<a
						href="/boardList.sp?currentPage=${i}&currPageBlock=${pvo.currPageBlock}"
						style="text-decoration: none;"> <c:out value="[${i}]" />&nbsp;
					</a>
				</c:forEach>
				<c:if test="${pvo.endPage < pvo.allPage}">
					<a
						href="/boardList.sp?currentPage=${pvo.endPage+1}&currPageBlock=${pvo.currPageBlock + 1}"
						style="text-decoration: none;">[다음]</a>
				</c:if>
			</h5>
 		</c:when>
	</c:choose>
	<!-- <form action="board">
		<input type="hidden" name="currentPage" />
		<input type="hidden" name="currentBlock" />
	</form> -->
	<form action="content.sp" name="content" method="post">
		<input type="hidden" name="currentPage" value='<c:out value="${pvo.currentPage}"/>' /> 
		<input type="hidden" name="currPageBlock" value='<c:out value="${pvo.currPageBlock}"/>' /> 
		<input type="hidden" name="num" value=''>
	</form>

</body>
</html>