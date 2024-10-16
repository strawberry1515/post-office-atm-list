<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改單筆資料</title>
<link rel="stylesheet" href="styles/index.css">
<link rel="stylesheet" href="styles/QueryById.css">
<style>
body{
display:flex;
flex-direction:column;
justify-content:center;
align-items:center;
}

h2{
margin:100px 0;
}

.inlineBlock {
	displat: inline-block;
}

.none {
	display: none;
}

.box{
grid-template-columns:1fr 1.5fr;
}

.boxLR {
height:900px;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: top;
}

.L,.R{
    margin-top: 40px;
width:400px;
}

.R{
grid-template-columns:0.3fr 1.5fr;
}

.divL,.divR,tr{

height:50px;
}

table, tbody, tr, td{
margin-top:5px;
margin:0;
padding:0;
vertical-align: top;
}
</style>
</head>
<body>
	<%
	request.getRequestDispatcher("QueryAllCity").include(request, response);
	%>
	<h2>修改單筆資料</h2>


	<form method="post" action="Update">
		<div class="boxLR">
			<div class="box L">
				<div class="divL">資料編號</div>
				<div class="divR">
					<input type="text" disabled value="${atm.key}" />
				</div>
				<div class="divL">縣市</div>
				<div class="divR">
					<input type="text" disabled value="${atm.city}" />
				</div>
				<div class="divL">鄉鎮市區</div>
				<div class="divR">
					<input type="text" disabled value="${atm.district}" />
				</div>
				<div class="divL">儲匯局號</div>
				<div class="divR">
					<input type="text" disabled value="${atm.postNo}" />
				</div>
				<div class="divL">局名</div>
				<div class="divR">
					<input type="text" disabled value="${atm.postName}" />
				</div>
				<div class="divL">聯絡電話</div>
				<div class="divR">
					<input type="text" disabled value="${atm.phone}" />
				</div>
				<div class="divL">郵局地址</div>
				<div class="divR">
					<input type="text" disabled value="${atm.address}" />
				</div>
				<div class="divL">
					<img src="images/map.png" height="20px" />經度
				</div>
				<div class="divR">
					<input type="text" disabled value="${atm.longitude}" />
				</div>
				<div class="divL">
					<img src="images/map.png" height="20px" />緯度
				</div>
				<div class="divR">
					<input type="text" disabled value="${atm.latitude}" />
				</div>
				<div class="divL">ATM</div>
				<div class="divR">
					<input type="checkbox" id="hasATM" disabled><input
						type="text" id="hasATMValue" style="display: none;" disabled
						value="${atm.hasAtm}" />
				</div>
				<div class="divL">
					<img src="images/deposit.png" height="20px" title="提供存款服務" />具存款
				</div>
				<div class="divR">
					<input type="checkbox" id="hasDeposit" disabled><input
						type="text" id="hasDepositValue" style="display: none;" disabled
						value="${atm.hasDeposit}" />
				</div>
				<div class="divL">
					<img src="images/passbook.png" height="20px" title="提供補摺服務" />具補摺
				</div>
				<div class="divR">
					<input type="checkbox" id="hasPassbookUpdate" disabled><input
						type="text" id="hasPassbookUpdateValue" style="display: none;"
						disabled value="${atm.hasPassbookUpdate}" />
				</div>
				<div class="divL">
					<img src="images/machine.png" height="20px" title="有補摺機" />自動補摺機
				</div>
				<div class="divR">
					<input type="checkbox" id="hasPassbookUpdateMachine" disabled><input
						type="text" id="hasPassbookUpdateMachineValue"
						style="display: none;" disabled
						value="${atm.hasPassbookUpdateMachine}" />
				</div>
				<div class="divL">
					<img src="images/cash.png" height="20px" title="提供200元鈔票" />提領200元
				</div>
				<div class="divR">
					<input type="checkbox" id="has200cash" disabled><input
						type="text" id="has200cashValue" style="display: none;" disabled
						value="${atm.has200cash}" />
				</div>
				<div class="divL">
					<img src="images/voice.png" height="20px" title="具視障語音服務" />具視障語音
				</div>
				<div class="divR">
					<input type="checkbox" id="hasVoiceAssistant" disabled><input
						type="text" id="hasVoiceAssistantValue" style="display: none;"
						disabled value="${atm.hasVoiceAssistant}" />
				</div>
				<div class="divL">
					<img src="images/atm.png" height="20px" title="郵局外ATM" />是否為局外ATM
				</div>
				<div class="divR">
					<input type="checkbox" id="isOutside" disabled><input
						type="text" id="isOutsideValue" style="display: none;" disabled
						value="${atm.outside}" />
				</div>
				<div class="divL">縣市排序代號</div>
				<div class="divR">
					<input type="text" disabled value="${atm.cityNo}" />
				</div>
				<div class="divL">資料建立時間</div>
				<div class="divR">
					<input type="text" disabled value="${atm.insertTime}" />
				</div>

			</div>
			<div class="box R">
				<div class="divL">--></div>
				<div class="divR">
					<input type="text" name="key" disabled value="${atm.key}" />
					<input style="display: none;" type="text" name="key" value="${atm.key}" />
				</div>
				<div class="divL">--></div>
				<div class="divR">
				<input style="display: none;" type="text" id="cityValue" value="${atm.city}" />
					<select id="cities" name="city" onchange="changeCity()">
								<c:forEach items="${cities}" var="city" varStatus="s">
									<option value="${city.cityNo},${city.city}">${city.city}</option>
								</c:forEach>
						</select>
				</div>
				<div class="divL">--></div>
				<div class="divR">
					<input type="text" name="district" value="${atm.district}" />
				</div>
				<div class="divL">--></div>
				<div class="divR">
					<input type="text" name="postNo" value="${atm.postNo}" />
				</div>
				<div class="divL">--></div>
				<div class="divR">
					<input type="text" name="postName" value="${atm.postName}" />
				</div>
				<div class="divL">--></div>
				<div class="divR">
					<input type="text" name="phone" value="${atm.phone}" />
				</div>
				<div class="divL">--></div>
				<div class="divR">
					<input type="text" name="address" value="${atm.address}" />
				</div>
				<div class="divL">--></div>
				<div class="divR">
					<input type="text" name="longitude" value="${atm.longitude}" />
				</div>
				<div class="divL">--></div>
				<div class="divR">
					<input type="text" name="latitude" value="${atm.latitude}" />
				</div>
				<div class="divL">--></div>
				<div class="divR">
					<input type="checkbox" id="hasATMNew" name="hasAtm" checked value="true" />
					<input type="text" id="hasATMValue" style="display: none;" disabled
						value="${atm.hasAtm}" />
				</div>
				<div class="divL">
					<img src="images/deposit.png" height="20px" title="提供存款服務" />-->
				</div>
				<div class="divR">
					<input type="checkbox" id="hasDepositNew" name="hasDeposit" checked value="true" />
							<input type="text" id="hasDepositValue" style="display: none;" disabled
						value="${atm.hasDeposit}" />
				</div>
				<div class="divL">
					<img src="images/passbook.png" height="20px" title="提供補摺服務" />-->
				</div>
				<div class="divR">
					<input type="checkbox" id="hasPassbookUpdateNew" name="hasPassbookUpdate" checked	value="true" />
							<input type="text" id="hasPassbookUpdateValue" style="display: none;"
						disabled value="${atm.hasPassbookUpdate}" />
				</div>
				<div class="divL">
					<img src="images/machine.png" height="20px" title="有補摺機" />-->
				</div>
				<div class="divR">
					<input type="checkbox" id="hasPassbookUpdateMachineNew" name="hasPassbookUpdateMachine" checked value="true" />
							<input type="text" id="hasPassbookUpdateMachineValue"
						style="display: none;" disabled
						value="${atm.hasPassbookUpdateMachine}" />
				</div>
				<div class="divL">
					<img src="images/cash.png" height="20px" title="提供200元鈔票" />-->
				</div>
				<div class="divR">
					<input type="checkbox" id="has200cashNew" name="has200cash" checked value="true" />
							<input type="text" id="has200cashValue" style="display: none;" disabled
						value="${atm.has200cash}" />
				</div>
				<div class="divL">
					<img src="images/voice.png" height="20px" title="具視障語音服務" />-->
				</div>
				<div class="divR">
					<input type="checkbox" id="hasVoiceAssistantNew" name="hasVoiceAssistant" checked value="true" />
							<input type="text" id="hasVoiceAssistantValue" style="display: none;"
						disabled value="${atm.hasVoiceAssistant}" />
				</div>
				<div class="divL">
					<img src="images/atm.png" height="20px" title="郵局外ATM" />-->
				</div>
				<div class="divR">
					<input type="checkbox" id="isOutsideNew" name="isOutside" checked value="true" />
							<input type="text" id="isOutsideValue" style="display: none;" disabled
						value="${atm.outside}" />
				</div>
				<div class="divL">--></div>
				<div class="divR">
					<input type="text" style="display: none;" id="cityNo999"
							name="cityNo" value="${atm.cityNo}" /> <input class="cityNo" type="text"
							id="cityNo-1" name="cityNo" disabled value="${atm.cityNo}" /> <c:forEach
								items="${cities}" var="city" varStatus="s">
								<input class="cityNo none" type="text" id="cityNo${city.cityNo}"
									name="cityNo" disabled value="${city.cityNo}" />
							</c:forEach>
				</div>
				<div class="divL">--></div>
				<div class="divR">
					<input type="text" name="insertTime" disabled
							value="${atm.insertTime}" />
				</div>
			</div>
		</div>

		<div><input type="submit" value="確定修改" /></div>
	<div style="margin-top:100px;"><input type="button" value="回上頁" onclick="history.back()"></div>
		<div style="height:100px;"></div>

	</form>
	<script>
	document.getElementById("hasATM").checked = (document.getElementById("hasATMValue").value === "true");
	document.getElementById("hasDeposit").checked = (document.getElementById("hasDepositValue").value === "true");
	document.getElementById("hasPassbookUpdate").checked = (document.getElementById("hasPassbookUpdateValue").value === "true");
	document.getElementById("hasPassbookUpdateMachine").checked = (document.getElementById("hasPassbookUpdateMachineValue").value === "true");
	document.getElementById("has200cash").checked = (document.getElementById("has200cashValue").value === "true");
	document.getElementById("hasVoiceAssistant").checked = (document.getElementById("hasVoiceAssistantValue").value === "true");
	document.getElementById("isOutside").checked = (document.getElementById("isOutsideValue").value === "true");

	document.getElementById("cities").value=document.getElementById("cityNo999").value+","+document.getElementById("cityValue").value;
	
	document.getElementById("hasATMNew").checked = (document.getElementById("hasATMValue").value === "true");
	document.getElementById("hasDepositNew").checked = (document.getElementById("hasDepositValue").value === "true");
	document.getElementById("hasPassbookUpdateNew").checked = (document.getElementById("hasPassbookUpdateValue").value === "true");
	document.getElementById("hasPassbookUpdateMachineNew").checked = (document.getElementById("hasPassbookUpdateMachineValue").value === "true");
	document.getElementById("has200cashNew").checked = (document.getElementById("has200cashValue").value === "true");
	document.getElementById("hasVoiceAssistantNew").checked = (document.getElementById("hasVoiceAssistantValue").value === "true");
	document.getElementById("isOutsideNew").checked = (document.getElementById("isOutsideValue").value === "true");

	
		function changeCity() {
			let currentCityNo = document.getElementById("cities").value
					.split(",")[0];

			let cityNos = document.getElementsByClassName("cityNo");
			for (let i = 0; i < cityNos.length; i++) {
				const cityNo = cityNos[i];
				cityNo.className = "cityNo none";
			}
			document.getElementById("cityNo999").value = document
					.getElementById("cityNo" + currentCityNo).value;
			document.getElementById("cityNo" + currentCityNo).className = "cityNo inlineBlock";
		}
	</script>
</body>
</html>
