<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		
		<div class="h2 text-center text-danger">Sales Form</div>
		<div class="card">
			<div class="card-header bg-light h3 text-primary">Sales Form</div>

			<div class="card-body">
				<div class="container">
					<form action="./saveSales" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
						<input type="hidden" name="salesId" id="salesId" value="${usales.salesId}">
						<div class="row">

							<div class="col-4">
								<label for="blockNameId" class="font-weight-bold">Customer Name</label>
								<select name="customer" id="customerId" class="form-control"
									onchange="getPanchayatByBlockId(this.value)">
									<option value="0">--select--</option>
								 <c:forEach items="${customerList}" var="Customer">
										<option value="${Customer.customerId }" <c:if test="${Customer.customerId eq usales.customer.customerId}">selected='selected'</c:if>>${Customer.customerName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-4">
								<label for="Productname" class="font-weight-bold">Product</label>
								<select name="product" id="productId" class="form-control"
									onchange="getProductQntyByProductId(this.value)">
									<option value="0">-select-</option>
								 <c:forEach items="${productList}" var="Product">
										<option value="${Product.productId }" <c:if test="${Product.productId  eq usales.product.productId}">selected='selected'</c:if>>${Product.productName}(${Product.quantity})</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-2">
								<label id="qid">Aval Qnty</label>
								<input type="text" name="qn" id="qnId" readonly="readonly" class="form-control">
							</div>
							<div class="col-4">
								<label for="popId" class="font-weight-bold">Quantity</label>
								<input name="salesQnty" id="salesQntyId" class="form-control" value="${usales.salesQnty}">
							</div>
						</div>
						<div class="mt-2 text-center">

							<input type="submit" class="btn btn-success">
							 <input type="reset" class="btn btn-warning">
						</div>
					</form>
				</div>
			</div>
		</div>
	
		<div class="mt-2">
			<div class="h3">All Sales Details</div>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Sl.#</th>
						<th>Customer Name</th>
						<th>Product Name</th>
						<th>Sales Date</th>
						<th>Quantity</th>
						<th>Unit Price</th>
						<th>Total Price</th>
						<th>Action</th>
						
					</tr>

				</thead>

				<tbody>
					<c:forEach items="${salesList}" var="sales" varStatus="counter">
						<tr>
							<td>${counter.count}</td>
							<td>${sales.customer.customerName}</td>
							<td>${sales.product.productName}</td>
							<td>${sales.salesDate}</td>
							<td>${sales.salesQnty}</td>
							<td>${sales.product.unitPrice}</td>
							<td>${sales.product.unitPrice*sales.salesQnty}</td>
							<td><a class="btn btn-danger" href="./delSales?sId=${sales.salesId }">Del</a>&nbsp;
							<a class="btn btn-warning" href="./updateSales?sId=${sales.salesId }">Update</a>
							</td>
						</tr>
					</c:forEach>

				</tbody>

			</table>



		</div>




	</div>
	<script type="text/javascript">
	
	function validateForm(){
		var cId = $('#customerId').val();
		var pId = $('#productId').val();
		var QId = $('#salesQntyId').val();
		var status=true;
	if(cId==0){
		alert("Please select a customer name!");
		status=false;
	}
	if(pId==0){
		alert("Please select a product name!");
		status=false;
	}
	if(QId==""){
		alert("Quantity is empty");
		status=false;
	}else{
	 var AQnty=Number($('#qnId').val());
		var sqn=Number(sQnty);
		if(sqn>AQnty){
			alert("Quantity shld be same/less than quantity shown!");
		} 
	}
		return status;
	}
	
	
		function getProductQntyByProductId(pId) {
			$.ajax({
				type : 'GET',
				url : 'getProductQntyByProductId',
				data : {
					productId : pId
				},
				success : function(resp) {

					$('#qnId').val(resp);
				}
			});

		}
		$(document).ready(function(){
			setTimeout(function(){
				document.getElementById("alId").style.display = 'none';
				}, 2000);
		});
	</script>
</body>

</html>