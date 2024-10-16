<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.ispan.wu.bean.AtmBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>郵局ATM資料</title>


<style>
body{
display:flex;
flex-direction:column;
justify-content:center;
align-atems:center;
}
.box{
display:grid;
grid-template-columns:repeat(25,1fr);
grid-auto-rows:20px;
width:min(60vw,1024px);
justify-items:space-between;
align-items:space-between;
}

span:hover{
background-color:yellow;}
</style>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>郵局ATM資料</h2>

<h3 style="color:red;">${result.msg}</h3>
<form method="post">
<table id="table0" border="1">
<c:if test="${atms!=null}" var="condition">
<tr style="background-color:#a8fefa">
<th>編號<th>縣市<th>鄉鎮市區<th>局名<th>聯絡電話<th>ATM/郵局地址<th>備註
<th>修改<th>刪除
</c:if>
<c:forEach items="${atms}" var="atm" varStatus="s">
<tr><td>${atm.key}
<td>${atm.city}
<td>${atm.district}
<td><a href="QueryById?id=${atm.key}">${atm.postName}</a>
<td>${atm.phone}
<td>${atm.address}
<td><c:if test="${atm.hasDeposit}"><img src="images/deposit.png" height="20px" title="提供存款服務"/></c:if>
<c:if test="${atm.hasPassbookUpdate}"><img src="images/passbook.png" height="20px" title="提供補摺服務"/></c:if>
<c:if test="${atm.hasPassbookUpdateMachine}"><img src="images/machine.png" height="20px" title="有補摺機"/></c:if>
<c:if test="${atm.has200cash}"><img src="images/cash.png" height="20px" title="提供200元鈔票"/></c:if>
<c:if test="${atm.hasVoiceAssistant}"><img src="images/voice.png" height="20px" title="具視障語音"/></c:if>
<c:if test="${atm.outside}"><img src="images/atm.png" height="20px" title="郵局外ATM"/></c:if>

<td><a href="DataForUpdate?id=${atm.key}"><input type="button" id="updateBtn${s.count}" value="修改" ></a>
<td><a href="DeleteById?id=${atm.key}"><input type="button" id="delBtn${s.count}" value="刪除" disabled></a><input type="checkbox" id="confirm${s.count}" name="confirm" value="true" onclick="confirmDelete()"><label for="confirm${s.count}">確認</label>
<c:if test="${s.count%20==0}" var="condition">
</table>
<table style="display:none;" id="table${s.count}" border="1">
<tr style="background-color:#a8fefa">
<th>編號<th>縣市<th>鄉鎮市區<th>局名<th>聯絡電話<th>ATM/郵局地址<th>備註
<th>修改<th>刪除
</c:if>
<c:set var="count" value="${s.count}"/>
</c:forEach>
</table>
<div class="box">
<c:forEach var="page" begin="1" end="${count}" step="20" varStatus="s">
<span id="page${page}" style="cursor:pointer;" onclick="switchPage()">${page}-</span>
</c:forEach>
</div>
<a href="index.jsp"><input type="button" value="回首頁"></a>
</form>
</div>
<script>
function confirmDelete(){
	num = event.currentTarget.getAttribute("id").substring(7);
	document.getElementById("delBtn"+num).disabled=!document.getElementById("delBtn"+num).disabled;
}

function switchPage(){
	num = event.currentTarget.getAttribute("id").substring(4);
	
	tables = document.getElementsByTagName("table");
    for (let i = 0; i < tables.length; i++) {
        const table = tables[i];
        table.style.display = "none";
    }
    
    pages = document.getElementsByTagName("span");
    for (let i = 0; i < pages.length; i++) {
        const pageSpan = pages[i];
        pageSpan.style.fontWeight = "normal";
    }
    //if(pages<=20){
    //	document.
    //}
	
	document.getElementById("table"+(num-1)).style.display="table";
	document.getElementById("page"+num).style.fontWeight = "bold";

}
</script>
</body>
</html>
