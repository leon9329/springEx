<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table align=center border=1 width=500>
	<tr>
		<td>번호</td>
		<td>${bcont.board_num }</td>
		<td>이름</td>
		<td>${bcont.board_name }</td>
		<td>조회수</td>
		<td>${bcont.board_readcount }</td>
	</tr>
	
	<tr>
		<td>제목</td>
		<td colspan=2>${bcont.board_content }</td>
		<td>등록일</td>
		<td colspan=2>
		<fmt:formatDate value="${bcont.board_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr>
	<td>내용</td>
	<td colspan=5>
		<pre>${bcont.board_content }</pre>
	</tr>
</table>

	<div align=center>
		<input type=button value=수정 onClick="location='board_cont.nhn?board_num=${bcont.board_num }&page=${page}&state=edit'"/>
		<input type=button value=삭제 onClick="location='board_cont.nhn?board_num=${bcont.board_num }&page=${page}&state=del'"/>
		<input type=button value=답변 onClick="location='board_cont.nhn?board_num=${bcont.board_num }&page=${page}&state=reply'"/>
		<input type=button value=목록 onClick="location='board_list.nhn?page=${page}'"/>
		
	</div>
	
	

</body>
</html>