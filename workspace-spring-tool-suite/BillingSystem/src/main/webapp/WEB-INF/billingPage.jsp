<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>  
 <?xml version="1.0" encoding="UTF-8"?> 
 
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Insert title here</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
                integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
                crossorigin="anonymous">
        </head>

        <body>
            <div id="toggleButtons">
                <button id="summaryPageBtn" class="btn" onclick="togglePage()">Go To Summary Page</button>
 
            </div>
            <div class="h2 text-center text-warning">Billing Form</div>
            <div class="card">
                <div class="card-body">
                    <div class="container">
                        <form id="billingform" action="./saveBill" method="post" onsubmit="validateForm(event)">
                        <input type="hidden" name="dateofSales" value="${dateofSales}">
                            <div class="row mt-3">
                                <div class="col-4">
                                    <label for="" class="font-weight-bold">Customer Name</label> <input type="text"
                                        class="form-control" name="customerName" id="customerName" value="">
                                </div>
                                <div class="col-4">
                                    <label for="" class="font-weight-bold">Item
                                        Name</label> <select name="itemId" id="itemId" class="form-control "
                                        onchange="checkQuantity()">
                                       <option value="0">--select--</option>
                                         <c:forEach items="${itemList}" var="items">
                                            <option value="${items.itemId}">${items.itemName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-4">
                                    <label for="quantityId" class="font-weight-bold">Quantity</label>
                                    <input type="text" class="form-control" name="salesQuantity" id="salesQuantityId" value="" onblur="checkQuantity()">
                                </div>
                                 <div id="quantityValidationMessage"></div>
                            </div>
                            <div class="mt-2 text-center">
                                <input type="submit" class="btn btn-success" value="Add">
                                <input type="submit" class="btn btn-warning" value="Save">
                                <input type="reset" class="btn btn-danger">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="container mt-5">
                <h2>Items Table</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Serial Number</th>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                            <th>Value</th>
                        </tr>
                    </thead>
                    <tbody>
                         <c:forEach var="item" items="${customeritemslist}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${item.itemName}</td>
                                <td>${item.salesQty}</td>
                                <td>${item.unitPrice}</td>
                                <td>${item.salesQty * item.unitPrice}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <script>
  
            function togglePage() {
                var currentPage = window.location.href;
                if (currentPage.includes("getBillingPage")) {
                    window.location.href = "<c:url value='/getSummaryPage'/>"; // Redirect to billing page mapping
                } else {
                    window.location.href = "<c:url value='/getBillingPage'/>"; // Redirect to summary page mapping
                }
            }
            </script>
             <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
function checkQuantity() {
    var itemId = document.getElementById('itemId').value;
    $.ajax({
        url: 'getAvailableQuantity?itemId='+itemId, 
        method: 'GET',
        success: function(response) {
            var availableQuantity = parseInt(response, 10); // Parse response to integer
            var enteredQuantity = parseInt(document.getElementById('salesQuantityInput').value, 10);

            if (isNaN(enteredQuantity)) {
                document.getElementById('quantityValidationMessage').innerText = "Please enter a valid number.";
            } else if (enteredQuantity > availableQuantity) {
                document.getElementById('quantityValidationMessage').innerText = "Quantity entered exceeds available quantity.";
            } else if (enteredQuantity <= 0) {
                document.getElementById('quantityValidationMessage').innerText = "Quantity must be greater than zero.";
            } else {
                document.getElementById('quantityValidationMessage').innerText = "Quantity is sufficient.";
                // Optionally, you can proceed with form submission or further actions here
            }
        },
        error: function(xhr, status, error) {
            console.error('Error fetching available quantity:', error);
        }
    });
}
function validateForm(event) {
    const customerName = document.getElementById('customerName').value;
    const itemId = document.getElementById('itemId').value;
    const salesQuantity = document.getElementById('salesQuantityId').value;
    const quantityValidationMessage = document.getElementById('quantityValidationMessage');

    quantityValidationMessage.innerHTML = '';

    if (!customerName) {
        alert('Customer Name is required');
        event.preventDefault();
        return false;
    }

    if (itemId === '0') {
        alert('Please select an item');
        event.preventDefault(); 
        return false;
    }
    if (salesQuantity === '') {
		alert('Sales Quantity is required!');
		event.preventDefault();
		return false;
	}
}

</script>
             
        </body>

        </html>