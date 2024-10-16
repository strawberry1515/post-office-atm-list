<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
p{
margin:0;
padding:0;
}
.box{
display:grid;
grid-template-columns:1fr 1fr 6fr 3fr 6fr 3fr 1fr 3fr;
grid-template-rows:repeat(30,1fr);
width:80vw;
height:80vh;
}
.box>div,p{
border: 2px solid orange;
}

#div1{
grid-column-start: 1;
  grid-row-start: 1;
  grid-column-end: 4;
  grid-row-end: 3;
}
</style>
</head>
<body>



<form method="post">
<div class="box" id="table0" border="1">

<div>縣市</div><div>鄉鎮市區</div><div>局名</div><div>聯絡電話</div><div>ATM/郵局地址</div><div>備註</div><div>修改</div><div>刪除</div>
<c:forEach items="${atms}" var="atm" varStatus="s">
<p>${atm.city}</p>
<p>${atm.district}</p>
<p><a href="QueryById?id=${atm.key}">${atm.postName}</a></p>
<p>${atm.phone}</p>
<p>${atm.address}</p>
<p>
<c:if test="${atm.hasDeposit}">存, </c:if>
<c:if test="${atm.hasPassbookUpdate}">補, </c:if>
<c:if test="${atm.hasPassbookUpdateMachine}">補機, </c:if>
<c:if test="${atm.has200cash}">200, </c:if>
<c:if test="${atm.hasVoiceAssistant}">語, </c:if>
<c:if test="${atm.outside}">ATM, </c:if>
</p>
<p><a href="DataForUpdate?id=${atm.key}"><input type="button" id="updateBtn${s.count}" value="修改" ></a></p>
<p><a href="DeleteById?id=${atm.key}"><input type="button" id="delBtn${s.count}" value="刪除" disabled></a><input type="checkbox" id="confirm${s.count}" name="confirm" value="true" onclick="confirmDelete()"><label for="confirm${s.count}">確認</label></p>
<c:if test="${s.count%20==0}" var="condition">
</div>
<div class="box" style="display:none;" id="table${s.count}" border="1">
<div>縣市</div><div>鄉鎮市區</div><div>局名</div><div>聯絡電話</div><div>ATM/郵局地址</div><div>備註</div><div>修改</div><div>刪除</div>
</c:if>
<c:set var="count" value="${s.count}"/>
</c:forEach>
</div>
<c:forEach var="page" begin="1" end="${count}" step="20" varStatus="s">
<span id="page${page}" style="cursor:pointer;" onclick="switchPage()">${page}-</span>
</c:forEach>
<a href="index.jsp"><input type="button" value="回首頁"></a>
</form>



<script>
function confirmDelete(){
	num = event.currentTarget.getAttribute("id").substring(7);
	document.getElementById("delBtn"+num).disabled=!document.getElementById("delBtn"+num).disabled;
}

function switchPage(){
	num = event.currentTarget.getAttribute("id").substring(4);
	
	tables = document.getElementsByTagName("p");
    for (let i = 0; i < tables.length/8; i++) {
        const table = tables[i];
        table.style.display = "none";
    }
    
    pages = document.getElementsByTagName("span");
    for (let i = 0; i < pages.length; i++) {
        const pageSpan = pages[i];
        pageSpan.style.fontWeight = "normal";
    }
	
	document.getElementById("table"+(num-1)).style.display="table";
	document.getElementById("page"+num).style.fontWeight = "bold";

}
</script>
</body>
</html>