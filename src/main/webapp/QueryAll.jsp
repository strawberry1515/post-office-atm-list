<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.ispan.wu.bean.AtmBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>郵局ATM資料</title>
<link rel="stylesheet" href="styles/index.css">
<link rel="stylesheet" href="styles/QueryById.css">
<link rel="stylesheet" href="styles/QueryAll.css">

</head>
<body>
	<div align="center">
		<h2>郵局ATM資料</h2>
<hr>
		<h3 style="color: red;">${result.msg}</h3>
		<form method="post">
<c:if test="${atms!=null&&atms.size()!=0}">
			<fieldset class="test grid" id="table0" border="1">
				<div class="data head">編號</div>
				<div class="data head">縣市</div>
				<div class="data head">鄉鎮市區</div>
				<div class="data head">ATM位置/局名</div>
				<div class="data head">聯絡電話</div>
				<div class="data head">ATM/郵局地址</div>
				<div class="data head">備註</div>
				<div class="data head">修改</div>
				<div class="data head">刪除</div>

				<c:forEach items="${atms}" var="atm" varStatus="s">
					<div class="data">${atm.key}</div>
					<div class="data">${atm.city}</div>
					<div class="data">${atm.district}</div>
					<div class="data">
						<a href="QueryById?id=${atm.key}">${atm.postName}</a>
					</div>
					<div class="data">${atm.phone}</div>
					<div class="data">${atm.address}</div>
					<div class="data icons">
					<a href="https://www.google.com/maps/@${atm.latitude},${atm.longitude},20z?entry=ttu" target="_blank">
					<img src="images/map.png" height="20px" title="在googl map中查看" /></a>
						<c:if test="${atm.hasDeposit}">
							<img src="images/deposit.png" height="20px" title="提供存款服務" />
						</c:if>
						<c:if test="${atm.hasPassbookUpdate}">
							<img src="images/passbook.png" height="20px" title="提供補摺服務" />
						</c:if>
						<c:if test="${atm.hasPassbookUpdateMachine}">
							<img src="images/machine.png" height="20px" title="有補摺機" />
						</c:if>
						<c:if test="${atm.has200cash}">
							<img src="images/cash.png" height="20px" title="提供200元鈔票" />
						</c:if>
						<c:if test="${atm.hasVoiceAssistant}">
							<img src="images/voice.png" height="20px" title="具視障語音服務" />
						</c:if>
						<c:if test="${atm.outside}">
							<img src="images/atm.png" height="20px" title="郵局外ATM" />
						</c:if>
					</div>
					<div class="data">
						<a href="DataForUpdate?id=${atm.key}"><input type="button"
							id="updateBtn${s.count}" value="修改"></a>
					</div>
					<div class="data">
						<a href="DeleteById?id=${atm.key}"><input type="button"
							id="delBtn${s.count}" value="刪除" disabled></a>
							<input type="checkbox" id="confirm${s.count}" name="confirm"
							value="true" onclick="confirmDelete()"><label class="chkLblR" 
							for="confirm${s.count}">確認</label>
					</div>
					<c:if test="${s.count%20==0}" var="condition">
			</fieldset>

			<fieldset class="test none" id="table${s.count}" border="1">
				<div class="data head">編號</div>
				<div class="data head">縣市</div>
				<div class="data head">鄉鎮市區</div>
				<div class="data head">ATM位置/局名</div>
				<div class="data head">聯絡電話</div>
				<div class="data head">ATM/郵局地址</div>
				<div class="data head">備註</div>
				<div class="data head">修改</div>
				<div class="data head">刪除</div>
				</c:if>
				<c:set var="count" value="${s.count}" />
				</c:forEach>
			</fieldset>
</c:if>
			<div class="footer box">
				<c:forEach var="page" begin="1" end="${count}" step="20"
					varStatus="s">
					<span class="pageSwitch" id="page${page}" style="cursor: pointer;"
						onclick="switchPage()">${page}-</span>
				</c:forEach>
			<div class="lastRow"><a href="index.jsp"><input type="button" value="回首頁"></a></div>
			<div style="height:10px;"></div>
			</div>
		</form>
	</div>
	<script>
	let pageSwitch = document.getElementsByClassName("pageSwitch");
	if(pageSwitch.length <= 25){
		document.getElementsByClassName("box")[0].style.width= pageSwitch.length * 30-20 +"px";   
		document.getElementsByClassName("box")[0].style.display="flex";   
		document.getElementsByClassName("box")[0].style.justifyContent="center";  
		document.getElementsByClassName("box")[0].style.flexWrap="wrap";   
		
	}
function confirmDelete(){
	num = event.currentTarget.getAttribute("id").substring(7);
	document.getElementById("delBtn"+num).disabled=!document.getElementById("confirm"+num).checked;
}

function switchPage(){
	num = event.currentTarget.getAttribute("id").substring(4);
	
	tables = document.getElementsByTagName("fieldset");
    for (let i = 0; i < tables.length; i++) {
        const table = tables[i];
        table.className="test none";
    }
    
    pages = document.getElementsByTagName("span");
    for (let i = 0; i < pages.length; i++) {
        const pageSpan = pages[i];
        pageSpan.style.fontWeight = "normal";
    }
    
	
	document.getElementById("table"+(num-1)).className="test grid";
	document.getElementById("page"+num).style.fontWeight = "bold";

}
</script>
</body>
</html>
