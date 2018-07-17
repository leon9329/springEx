<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 align=center>게시판 목록</h2>
<div align=center>글 수:${listcount }개</div>


<table border=1 align=center width=500>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>날짜</td>
		<td>조회수</td>
	</tr>
	<!-- 화면 출력 번호 변수 정의 -->
	<c:set var="num" value="${listcount-(page-1)*10 }"/>
	
	<!-- 반복문 시작 -->
	<c:forEach var="b" items="${boardlist }">
	<tr>
		<td>${num }</td>
		<c:set var="num" value="${num-1 }"/>
		<td>
			<c:if test="${b.board_re_lev != 0 }">
				<c:forEach var="k" begin="1" end="${b.board_re_lev }">
					&nbsp; &nbsp;
				</c:forEach>
			</c:if>
				
				<!-- 제목 출력 부분 -->
				<a href="board_cont.nhn?board_num=${b.board_num }&page=${page }&state=cont">
					${b.board_subject }
				</a>
		</td>
		<td>
			${b.board_name }
		</td>
		<td>
			<fmt:formatDate value="${b.board_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		<td>
			${b.board_readcount }
		</td>
	</tr>
	</c:forEach>
</table>

<center>
<c:if test="${page <= 1 }">
	[이전]&nbsp;
</c:if>

<c:if test="${page > 1 }">
	<a href="board_list.nhn?page=${page-1 }">[이전]</a>
</c:if>
<c:forEach var="a" begin="${startpage }" end="${endpage }">
	<c:if test="${a==page }">
		[${a }]
	</c:if>
	<c:if test="${a != page }">
		<a href="board_list.nhn?page=${a }">[${a }]</c:if>
</c:forEach>

<c:if test="${page >= maxpage }">
	[다음]
</c:if>
<c:if test="${page < maxpage }">
	<a href="board_list.nhn?page=${page+1 }">[다음]</a>
</c:if>
<div align=center>
	<input type="button" value="글쓰기" onClick="location='board_write.nhn?page=${page}'">
</div>
</center>


</body>
</html>