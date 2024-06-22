<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Summary Page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
</head>
<body>
	<div id="toggleButtons">
		<button id="summaryPageBtn" class="btn" onclick="togglePage()">Go
			To Billing Page</button>

	</div>
	<div class="container mt-5">
		<div class="h2 text-center text-warning">Summary Form</div>

		<form action="" onsubmit="return submitForm()">
			<div class="container m-2">
				<div class="row">
					<div class="col-4">
						<label for="selectDate">Select Date:</label> 
						<input type="date"
							id="selectDate" name="selectDate" class="form-control">
							<br>
							<input type="submit" class="btn btn-success">
					</div>
			
						
				</div>
			</div>
		</form>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Serial Number</th>
					<th>Customer Name</th>
					<th>Number of Items</th>
					<th>Total Billed Amount</th>
				</tr>
			</thead>
			<tbody id="summaryTableBody">

				<c:forEach var="summary" items="${summaries}" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${summary.customerName}</td>
						<td>${summary.totalItemsPurchased}</td>
						<td>${summary.totalBilledAmount}</td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="3" class="text-right"><strong>Grand
							Total:</strong></td>
					<td id="grandTotalCell"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script>
		function togglePage() {
			var currentPage = window.location.href;
			if (currentPage.includes("getSummaryPage")) {
				window.location.href = "<c:url value='/getBillingPage'/>"; // Redirect to billing page mapping
			} else {
				window.location.href = "<c:url value='/getSummaryPage'/>"; // Redirect to summary page mapping
			}
		}
		function submitForm() {
	        var selectedDate = document.getElementById('selectDate').value;
	        var url = "<c:url value='/getCustomerSummary'/>?date=" + selectedDate;
	        window.location.href = url;
	        return false; 
	    }
	</script>
	<script type="text/javascript">
    // Ensure scripts run after the DOM is fully loaded
    $(document).ready(function() {
        calculateGrandTotal();
    });

    function calculateGrandTotal() {
        var grandTotal = 0;
        // Iterate over each row in the table body
        $('#summaryTableBody tr').each(function() {
            // Parse the total billed amount from the fourth column of each row
            var totalBilledAmount = parseFloat($(this).find('td:eq(3)').text());
            // Add to grand total if it's a valid number
            if (!isNaN(totalBilledAmount)) {
                grandTotal += totalBilledAmount;
                console.log("totalBilledAmount"+totalBilledAmount)
                 console.log("grandTotal"+grandTotal)
            }
        });
        $('#grandTotalCell').text(grandTotal.toFixed(2));
    }
</script>
	

</body>
</html>
