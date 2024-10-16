<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單筆資料查詢結果</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>郵局ATM資料</h2>


<table>
<tr><td>資料編號<td><input type="text" disabled value="${atm.key}"/>
<tr><td>縣市<td><input type="text" disabled value="${atm.city}"/>
<tr><td>鄉鎮市區<td><input type="text" disabled value="${atm.district}"/>
<tr><td>儲匯局號<td><input type="text" disabled value="${atm.postNo}"/>
<tr><td>局名<td><input type="text" disabled value="${atm.postName}"/>
<tr><td>聯絡電話<td><input type="text" disabled value="${atm.phone}"/>
<tr><td>郵局地址<td><input type="text" disabled value="${atm.address}"/>
<tr><td>經度<td><input type="text" disabled value="${atm.longitude}"/>
<tr><td>緯度<td><input type="text" disabled value="${atm.latitude}"/>
<tr><td>ATM<td><input type="checkbox" id="hasATM" disabled><input type="text" id="hasATMValue" style="display:none;" disabled value="${atm.hasAtm}"/>
<tr><td>具存款<td><input type="checkbox" id="hasDeposit" disabled><input type="text" id="hasDepositValue" style="display:none;" disabled value="${atm.hasDeposit}"/>
<tr><td>具補摺<td><input type="checkbox" id="hasPassbookUpdate" disabled><input type="text" id="hasPassbookUpdateValue" style="display:none;" disabled value="${atm.hasPassbookUpdate}"/>
<tr><td>自動補摺機<td><input type="checkbox" id="hasPassbookUpdateMachine" disabled><input type="text" id="hasPassbookUpdateMachineValue" style="display:none;" disabled value="${atm.hasPassbookUpdateMachine}"/>
<tr><td>提領200元<td><input type="checkbox" id="has200cash" disabled><input type="text" id="has200cashValue" style="display:none;" disabled value="${atm.has200cash}"/>
<tr><td>具視障語音<td><input type="checkbox" id="hasVoiceAssistant" disabled><input type="text" id="hasVoiceAssistantValue" style="display:none;" disabled value="${atm.hasVoiceAssistant}"/>
<tr><td>是否為局外ATM<td><input type="checkbox" id="isOutside" disabled><input type="text" id="isOutsideValue" style="display:none;" disabled value="${atm.outside}"/>
<tr><td>縣市排序代號<td><input type="text" disabled value="${atm.cityNo}"/>
<tr><td>資料建立時間<td><input type="text" disabled value="${atm.insertTime}"/>

</table>
<h3>${result.msg}</h3>
<input type="submit" value="回上頁" onclick="history.back()">
<br>
<a href="index.jsp"><input type="submit" value="回首頁"></a>

</div>
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