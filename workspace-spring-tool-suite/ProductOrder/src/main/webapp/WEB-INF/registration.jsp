<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sales Form</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-2.2.4.js"
	integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
	crossorigin="anonymous"></script>

</head>

<body>



	<div class="container mt-5">
		<c:if test="${msg ne null}">
			<div class="alert alert-success" id="alId">${msg}</div>
		</c:if>

		<div class="h2 text-center text-warning">Student Registration
			Form</div>
		<div class="card">

			<div class="card-body">
				<div class="container">
					<form action="saveOrderDtls" method="GET"
						enctype="multipart/form-data">


						<div class="row mt-3">

							<div class="col-2">
							<label class="font-weight-bold">Date between</label>
							</div>
							<div class="col-4">
								<input type="date" class="form-control" name="startDate" id="startDate">
							</div>
							<div class="col-1">
							<label class="font-weight-bold">And</label>
							</div>
							<div class="col-4">
								<input type="date" class="form-control" name="endDate" id="endDate">
							</div>

						</div>

						<div class="mt-2 text-center">
							<input type="submit" class="btn btn-success" value="Fetch Orders" id="filterButton">
							<input type="reset" class="btn btn-warning">
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="mt-2">
			<div class="h3">Order Details</div>

			<table class="table table-bordered table-striped mt-2">
				<thead>
					<tr>
						<th>Sl.#</th>
						<th>Product Name</th>
						<th>Order Date</th>
						<th>Order Qty</th>
						<th>Order Value</th>
						<th>Action</th>
					</tr>

				</thead>

				<tbody id="tbody">
					<c:forEach items="${orderList}" var="order" varStatus="counter">
						<tr>
							<td>${counter.count}</td>
							<td>${order.product.prodName}</td>
							<td>${order.orderDate}</td>
							<td>${order.orderQty}</td>
							<td>${order.orderQty * order.product.prodRate}</td>
							<td></td>
						</tr>
					</c:forEach>

				</tbody>

			</table>

		</div>



		<!-- <script type="text/javascript">
			$(document).ready(function() {
				setTimeout(function() {
					document.getElementById("alId").style.display = 'none';
				}, 2000);
			});

			function validateForm() {
				console.log("yes");

				var sId = $('#seasonId').val();
				var cId = $('#cropNameId').val();
				var fnameId = $('#farmerNameId').val();
				var fathNameId = $('#fatherNameId').val();
				var adharId = $('#adharNoId').val();
				var addId = $('#addressId').val();
				var fcId = $('input[name="farmerCategory"]:checked').val();
				var status = true;
				if (sId == 0) {
					alert("Plz select a customer !!!");
					status = false;
				}
				if (cId == 0) {
					alert("Plz select a product !!!");
					status = false;
				}
				if (fnameId == "") {
					alert("Plz provide farmer name");
					status = false;
				}
				if (fathNameId == "") {
					alert("Plz provide father name");
					status = false;
				}
				if (adharId == "") {
					alert("Plz provide adhar id");
					status = false;
				} else if (!isValidAadhar(adharId)) {
					alert("Aadhar ID should be 12 digits");
					status = false;
				}
				if (addId == "") {
					alert("Plz provide address");
					status = false;
				}
				if (!fcId) {
					alert("Plz select farmer category");
					status = false;
				}

				return status;
			}
			function isValidAadhar(aadhar) {
				var aadharPattern = /^\d{14}$/;
				return aadharPattern.test(aadhar);
			}

			
		</script>-->

		<script>
			$("#filterButton").click(function() {
				var startDate = $("#startDate").val();    
				var endDate = $("#endDate").val();  
				
				$.ajax({        
					url: "/saveOrderDtls",        
					type: "GET",       
					data: {
						startDate: startDate, 
						endDate: endDate 
						},        
						
						success: function(resp) {  
							// Update orderDetails div with the filtered data  
							  $("#tbody").empty();

					            // Loop through the received data and append rows to tbody
					            $.each(resp, function(index, order) {
					                var row = "<tr>" +
					                          "<td>" + (index + 1) + "</td>" +
					                          "<td>" + order.product.prodName + "</td>" +
					                          "<td>" + order.orderDate + "</td>" +
					                          "<td>" + order.orderQty + "</td>" +
					                          "<td>" + (order.orderQty * order.product.prodRate) + "</td>" +
					                          "<td></td>" +
					                          "</tr>";
					                $("#tbody").append(row);
					            });
							},   
							error: function(error) {    
								// Handle errors here         
								console.error("Error filtering orders:", error); 
								}    });});
		</script>
	</div>
</body>

</html>
