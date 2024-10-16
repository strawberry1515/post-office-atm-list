<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單筆資料查詢結果</title>
<link rel="stylesheet" href="styles/index.css">
<link rel="stylesheet" href="styles/QueryById.css">
<style>

hr{
margin-top:15px;
}

.box{
margin-top:20px;
}

.divL, .divR{
margin:2px 5px;
padding:0px 5px;
padding-top:3px;
}

input[type=text]{
margin:5px 10px;
margin-top:0;
padding:2px 5px;
}

input[type=checkbox]{
margin:5px 10px;
padding:2px 5px;
}

</style>
</head>
<body>
<div align="center">
<h2>郵局ATM資料</h2>
<hr>

<div class="box">
<div class="divL">資料編號</div><div class="divR"><input type="text" disabled value="${atm.key}"/></div>
<div class="divL">縣市</div><div class="divR"><input type="text" disabled value="${atm.city}"/></div>
<div class="divL">鄉鎮市區</div><div class="divR"><input type="text" disabled value="${atm.district}"/></div>
<div class="divL">儲匯局號</div><div class="divR"><input type="text" disabled value="${atm.postNo}"/></div>
<div class="divL">局名</div><div class="divR"><input type="text" disabled value="${atm.postName}"/></div>
<div class="divL">聯絡電話</div><div class="divR"><input type="text" disabled value="${atm.phone}"/></div>
<div class="divL">郵局地址</div><div class="divR"><input type="text" disabled value="${atm.address}"/></div>
<div class="divL"><img src="images/map.png" height="20px" />經度</div><div class="divR"><input type="text" disabled value="${atm.longitude}"/></div>
<div class="divL"><img src="images/map.png" height="20px" />緯度</div><div class="divR"><input type="text" disabled value="${atm.latitude}"/></div>
<div class="divL">ATM</div><div class="divR"><input type="checkbox" id="hasATM" disabled><input type="text" id="hasATMValue" style="display:none;" disabled value="${atm.hasAtm}"/></div>
<div class="divL"><img src="images/deposit.png" height="20px" title="提供存款服務" />具存款</div><div class="divR"><input type="checkbox" id="hasDeposit" disabled><input type="text" id="hasDepositValue" style="display:none;" disabled value="${atm.hasDeposit}"/></div>
<div class="divL"><img src="images/passbook.png" height="20px" title="提供補摺服務" />具補摺</div><div class="divR"><input type="checkbox" id="hasPassbookUpdate" disabled><input type="text" id="hasPassbookUpdateValue" style="display:none;" disabled value="${atm.hasPassbookUpdate}"/></div>
<div class="divL"><img src="images/machine.png" height="20px" title="有補摺機" />自動補摺機</div><div class="divR"><input type="checkbox" id="hasPassbookUpdateMachine" disabled><input type="text" id="hasPassbookUpdateMachineValue" style="display:none;" disabled value="${atm.hasPassbookUpdateMachine}"/></div>
<div class="divL"><img src="images/cash.png" height="20px" title="提供200元鈔票" />提領200元</div><div class="divR"><input type="checkbox" id="has200cash" disabled><input type="text" id="has200cashValue" style="display:none;" disabled value="${atm.has200cash}"/></div>
<div class="divL"><img src="images/voice.png" height="20px" title="具視障語音服務" />具視障語音</div><div class="divR"><input type="checkbox" id="hasVoiceAssistant" disabled><input type="text" id="hasVoiceAssistantValue" style="display:none;" disabled value="${atm.hasVoiceAssistant}"/></div>
<div class="divL"><img src="images/atm.png" height="20px" title="郵局外ATM" />是否為局外ATM</div><div class="divR"><input type="checkbox" id="isOutside" disabled><input type="text" id="isOutsideValue" style="display:none;" disabled value="${atm.outside}"/></div>
<div class="divL">縣市排序代號</div><div class="divR"><input type="text" disabled value="${atm.cityNo}"/></div>
<div class="divL">資料建立時間</div><div class="divR"><input type="text" disabled value="${atm.insertTime}"/></div>

</div>
<h3>${result.msg}</h3>
<input type="submit" value="回上頁" onclick="history.back()">
<br>
<br>
<a href="index.jsp"><input type="submit" value="回首頁"></a>

</div>
	<div style="height:100px;"></div>
<script>
document.getElementById("hasATM").checked = (document.getElementById("hasATMValue").value === "true");
document.getElementById("hasDeposit").checked = (document.getElementById("hasDepositValue").value === "true");
document.getElementById("hasPassbookUpdate").checked = (document.getElementById("hasPassbookUpdateValue").value === "true");
document.getElementById("hasPassbookUpdateMachine").checked = (document.getElementById("hasPassbookUpdateMachineValue").value === "true");
document.getElementById("has200cash").checked = (document.getElementById("has200cashValue").value === "true");
document.getElementById("hasVoiceAssistant").checked = (document.getElementById("hasVoiceAssistantValue").value === "true");
document.getElementById("isOutside").checked = (document.getElementById("isOutsideValue").value === "true");

</script>
</body>

</html>