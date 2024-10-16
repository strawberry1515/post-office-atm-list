<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, com.ispan.wu.bean.AtmBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>郵局ATM查詢系統</title>
<link rel="stylesheet" href="styles/index.css">
<style>
span{    
text-wrap: nowrap;
}

.onRight{
margin-left:3px;
margin-right:10px;
}
</style>
</head>
<body>
<% request.getRequestDispatcher("QueryAllCity").include(request, response); %>
<div class="main container">
<div class="box boxl">
<h2>郵局ATM查詢系統</h2>
<form method="post">
<table>
<tr><td><input type="submit" value="查詢所有資料(依縣市排序)" formaction="QueryAll"><input type="submit" value="查詢所有資料(依編號排序)" formaction="QueryAllByPage?page=1">
<tr><td>
<label>依名稱查詢：</label><input type="text" name="postName"><input type="submit" value="模糊查詢" formaction="QueryObscureByName">
<br>
<label for="cities">選擇縣市：</label>
<select id="cities" name="cityNo">
<option value="-1">全部(不指定)</option>
<c:forEach items="${cities}" var="city" varStatus="s">
<option value="${city.cityNo}">${city.city}</option>
</c:forEach>
</select>
<tr><td class=boxUD>
<div class="boxLR"><div><label>依特徵查詢:</label></div>
<div style="width:400px;">

<span><input type="checkbox" id="hasAtm" name="hasAtm" value="true"><label class="onRight" for="hasAtm">ATM</label></span>
<span><input type="checkbox" id="hasDeposit" name="hasDeposit" value="true"><label class="onRight" for="hasDeposit">具存款</label></span>
<span><input type="checkbox" id="hasPassbookUpdate" name="hasPassbookUpdate" value="true"><label class="onRight" for="hasPassbookUpdate">具補摺</label></span>
<span><input type="checkbox" id="hasPassbookUpdateMachine" name="hasPassbookUpdateMachine" value="true"><label class="onRight" for="hasPassbookUpdateMachine">自動補摺機</label></span>
<span><input type="checkbox" id="has200cash" name="has200cash" value="true"><label class="onRight" for="has200cash">提領200元</label></span>
<span><input type="checkbox" id="hasVoiceAssistant" name="hasVoiceAssistant" value="true"><label class="onRight" for="hasVoiceAssistant">具視障語音</label></span>
<span><input type="checkbox" id="isOutside" name="isOutside" value="true"><label class="onRight" for="isOutside">局外ATM</label></span>
</div>
</div>
<div>
<input type="submit" value="依特徵查詢" formaction="QueryObscureByBooleanData">
</div>
<tr><td><label>後臺管理功能：</label><input type="submit" value="新增單筆/上傳檔案" formaction="Insert.jsp"><input type="submit" value="一鍵新增" formaction="InsertAll"><input type="submit" value="一鍵刪除" formaction="DeleteAll">
</table>
</form>
</div>
<div class="box boxr"><img src="images/mailbox.jpg"></div>
</div>
<%@include file="Footer.jsp" %>
</body>
</html>