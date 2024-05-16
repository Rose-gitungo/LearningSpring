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

	<c:if test="${msg ne null}">
		<div class="alert alert-success" id="alId">${msg}</div>
	</c:if>

	<div class="container mt-5">

		<div class="h2 text-center text-warning">Tyre Sales Form</div>
		<div class="card">

			<div class="card-body">
				<div class="container">
					<form action="./saveTyre" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
						<div class="row mt-3">
						
							<input type="hidden" name="salesId" value="${salesId}">
							<input type="hidden" name="salesDate" value="${salesDate}">
								<div class="col-4">
								<label for="garageNameId" class="font-weight-bold">Garage Name</label>
								 <select name="garage" id="garageNameId" class="form-control " onchange="getSalesDetails(this.value)">
									<option value="0">-select-</option>
									<c:forEach items="${garageList}" var="garage">
										<option value="${garage.garageId}">${garage.garageName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-4">
								<label for="tyreNameId" class="font-weight-bold">Tyre Name</label>
								 <select name="tyre" id="tyreNameId" class="form-control" onchange="getQuantityAvailable(this.value)">
									<option value="0">-select-</option>
									<c:forEach items="${tyreList}" var="tyre">
										<option value="${tyre.tyreId}">${tyre.tyreName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-2">
								<label for="QtyAvailId" class="font-weight-bold">Qty Avail</label> <input
									type="text" class="form-control" name="" id="QtyAvailId" readonly>
							</div>
							<div class="col-2">
								<label for="quantityId" class="font-weight-bold">Quantity</label> <input
									type="text" class="form-control" name="salesQuantity" id="quantityId"
									maxlength="50">
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
		 <div class="mt-5">
			<div class="h3">Garage Sales Details</div>

			<table class="table table-bordered table-striped mt-2">
				<thead>
					<tr>
						<th>Sl.#</th>
						<th>Tyre name</th>
						<th>Quantity</th>
					</tr>

				</thead>

				<tbody id="salesTableBody">
					<c:forEach items="${salesList}" var="sales" varStatus="counter">
						<tr>
							<td>${counter.count}</td>
							<td>${sales.tyre.tyreName}</td>
							<td>${sales.salesQuantity}</td>
						</tr>
					</c:forEach>

				</tbody>

			</table>

		</div>
 
</div>
	
</body>
<script type="text/javascript">
function getSalesDetails(garageId) {
    $.ajax({
        type: 'GET',
        url: 'getSalesDetails',
        data: {
            garageId: garageId
        },
        success: function (resp) {
            $('#salesTableBody').html(resp);
        }
    });
}

function getQuantityAvailable(tyreId){
	$.ajax({
        type: 'GET',
        url: 'getQuantityAvailable',
        data: {
        	tyreId: tyreId
        },
        success: function (resp) {
            $('#QtyAvailId').val(resp);
        }
    });
}

$(document).ready(function() {
    // Attach blur event listener to the quantity input field
    $("#quantityId").blur(function() {
        validateForm();
    });
});

function validateForm() {
    var qty = $("#quantityId").val(); 
    var qtyAvail =$("#QtyAvailId").val();

    if (qty >= qtyAvail) {
        alert("Quantity available is less than quantity wanted!");
        $("#quantityId").val(""); 
    }
}


</script>
</html>
