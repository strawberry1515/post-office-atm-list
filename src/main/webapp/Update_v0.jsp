<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改單筆資料</title>
</head>
<body>
<h2>修改單筆資料</h2>


<form method="post" action="Update">
<table>
<tr><td>資料編號<td><input type="text" disabled value="${atm.key}"/>
<td>--><td><input type="text" name="key" value="${atm.key}" disabled/><input style="display:none;" type="text" name="key" value="${atm.key}"/>
<tr><td>縣市<td><input type="text" disabled value="${atm.city}"/>
<td>--><td><input type="text" name="city" value="${atm.city}" />
<tr><td>鄉鎮市區<td><input type="text" disabled value="${atm.district}"/>
<td>--><td><input type="text" name="district" value="${atm.district}" />
<tr><td>儲匯局號<td><input type="text" disabled value="${atm.postNo}"/>
<td>--><td><input type="text" name="postNo" value="${atm.postNo}" />
<tr><td>局名<td><input type="text" disabled value="${atm.postName}"/>
<td>--><td><input type="text" name="postName" value="${atm.postName}" />
<tr><td>聯絡電話<td><input type="text" disabled value="${atm.phone}"/>
<td>--><td><input type="text" name="phone" value="${atm.phone}" />
<tr><td>郵局地址<td><input type="text" disabled value="${atm.address}"/>
<td>--><td><input type="text" name="address" value="${atm.address}" />
<tr><td>經度<td><input type="text" disabled value="${atm.longitude}"/>
<td>--><td><input type="text" name="longitude" value="${atm.longitude}" />
<tr><td>緯度<td><input type="text" disabled value="${atm.latitude}"/>
<td>--><td><input type="text" name="latitude" value="${atm.latitude}" />
<tr><td>ATM<td><input type="text" disabled value="${atm.hasAtm}"/>
<td>--><td><input type="text" name="hasAtm" value="${atm.hasAtm}" />
<tr><td>具存款<td><input type="text" disabled value="${atm.hasDeposit}"/>
<td>--><td><input type="text" name="hasDeposit" value="${atm.hasDeposit}"/>
<tr><td>具補摺<td><input type="text" disabled value="${atm.hasPassbookUpdate}"/>
<td>--><td><input type="text" name="hasPassbookUpdate" value="${atm.hasPassbookUpdate}" />
<tr><td>自動補摺機<td><input type="text" disabled value="${atm.hasPassbookUpdateMachine}"/>
<td>--><td><input type="text" name="hasPassbookUpdateMachine" value="${atm.hasPassbookUpdateMachine}" />
<tr><td>提領200元<td><input type="text" disabled value="${atm.has200cash}"/>
<td>--><td><input type="text" name="has200cash" value="${atm.has200cash}" />
<tr><td>具視障語音<td><input type="text" disabled value="${atm.hasVoiceAssistant}"/>
<td>--><td><input type="text" name="hasVoiceAssistant" value="${atm.hasVoiceAssistant}" />
<tr><td>是否為局外ATM<td><input type="text" disabled value="${atm.outside}"/>
<td>--><td><input type="text" name="isOutside" value="${atm.outside}" />
<tr><td>縣市排序代號<td><input type="text" disabled value="${atm.cityNo}"/>
<td>--><td><input type="text" name="cityNo" value="${atm.cityNo}" />
<tr><td>資料建立時間<td><input type="text" disabled value="${atm.insertTime}"/>
<td>--><td><input type="text" name="insertTime" value="${atm.insertTime}" />

</table>
	
	<input type="submit" value="確定修改" />
	
</form>
<input type="submit" value="回上頁" onclick="history.back()">
<br>
</body>
</html>
