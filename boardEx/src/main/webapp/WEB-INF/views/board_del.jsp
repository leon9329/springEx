<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=UTF-8">
<title>삭제 폼</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>


<script>
 	 function del_check(){
		  if($.trim($("#pwd").val())==""){
			  alert("삭제 비번을 입력하세요!");
			  $("#pwd").val("").focus();
			  return false;
	 	 }
  	}
	</script>

	
</head>
<body>
<h2 align=center>게시글 삭제</h2>
<form method="post" action="board_del_ok.nhn" onsubmit="return del_check()">
<input type="hidden" name="board_num" value=${bcont.board_num }/>
<input type="hidden" name="page" value=${page }/>

<table align=center border=1>
	<tr>
		<td>글 제목</td>
		<td>${bcont.board_subject }</td>
		
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>
			<input type="password" name="pwd" id="pwd" size=14/>
		</td>
	</tr>
	<tr>
		<td colspan=2>
		<center>
		<input type="submit" value=삭제 />
		<input type="reset" value=취소 onclick="$('#pwd').focus();" />
		</center>
		</td>
	</tr>
</table>
</form>
</body>
</html>