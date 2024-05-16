<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration Form</title>
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

		<div class="h2 text-center text-warning">User Registration Form</div>
		<div class="card">

			<div class="card-body">
				<div class="container">
					<form action="./saveUser" method="post"
						enctype="multipart/form-data" onsubmit="return validateForm()">


						<div class="row mt-3">
							<div class="col-3">
								<label for="UserId" class="font-weight-bold">UserId</label> <input
									type="text" class="form-control" name="UserId" value="${u.userId}"
									id="UserId" maxlength="50">
							</div>

							<div class="col-3">
								<label for="passwordId" class="font-weight-bold">Password</label>
								<input type="text" class="form-control" name="password"
									id="passwordId" maxlength="50" value="${u.password}">
							</div>

							<div class="col-3">
								<label for="repasswordId" class="font-weight-bold">Repeat
									password</label> <input type="text" class="form-control" id="repasswordId" maxlength="50" value="">
							</div>
							 <div class="col-3">
								<label class="font-weight-bold">upload Photo</label> <input type="file"
									class="form-control" name="image" value="${u.fileName}" accept="image/png,image/jpeg">
							</div> 
						</div>
						<div class="row mt-2">
							<div class="col-3">
								<label for="fullNameId" class="font-weight-bold">Full
									Name</label> <input type="text" class="form-control" name="fullName"
									id="fullNameId" maxlength="50" value="${u.fullName}">
							</div>
							<div class="col-3">
								<label for="emailId" class="font-weight-bold">Email</label> <input
									type="text" class="form-control" name="email" value="${u.email}"
									id="emailId" maxlength="50">
							</div>

							<div class="col-3">
								<label for="MobileNoId" class="font-weight-bold">Mobile
									no</label> <input type="text" class="form-control" name="MobileNo"
									value="${u.mobileNo}" id="MobileNoId">
							</div>


							<div class="col-3">
								<label for="age" class="font-weight-bold">Date of Birth</label>
								<input type="date" class="form-control" name="dob" id="dobId" value="${u.dob}" <c:if test="${ not empty u.dob}"></c:if>>
							</div>

						</div>

						<div class="row mt-2">
						<div class="col-3">
						<label class="font-weight-bold">Gender</label>
						<div>
						
							<input type="radio" name="gender" value="male" id="genderId" <c:if test="${u.gender == 'male'}">
                   checked
               </c:if>>
								Male
							<input type="radio" name="gender" value="female" id="genderId" <c:if test="${u.gender == 'Female'}">
                   checked
               </c:if>>
								Female
						</div>
						</div>
						<div class="col-5">
						<label class="font-weight-bold" for="addressId">Address</label>
						<textarea  id="addressId" rows="5" cols="50" name="address" value="${u.address}"></textarea>
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
			<div class="h3">All User Registration</div>

			<table class="table table-bordered table-striped mt-2">
				<thead>
					<tr>
						<th>Sl.#</th>
						<th>Full Name</th>
						<th>Mobile No</th>
						<th>Gender</th>
						<th>Email</th>
						<th>Address</th>
						<th>File Path</th>
						<th>Action</th>
					</tr>

				</thead>

				<tbody>
					<c:forEach items="${userList}" var="user" varStatus="counter">
						<tr>
							<td>${counter.count}</td>
							<td>${user.fullName}</td>
							<td>${user.mobileNo}</td>
							<td>${user.gender}</td>
							<td>${user.email}</td>
							<td>${user.address}</td>
							<td>${user.fileName}</td>
							<td><a href="./updateUser?uId=${user.userId}" class="btn btn-warning">Update</a>
								
								 <a href="./deleteUser?uId=${user.userId}" class="btn btn-danger">Del</a>
							</td>
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

		<!-- 	<script>
			$(document).ready(function() {
				setTimeout(function() {
					document.getElementById("alId").style.display = 'none';
				}, 3000);
			});

			function getCityByStateId(sId) {
				console.log(sId);
				$.ajax({
					type : 'GET',
					url : 'getCityByStateId',
					data : {
						stateId : sId
					},
					success : function(resp) {
						$('#cityId').html(resp);
					}
				});
			}

			function displayCourseFees(cId) {

				$.ajax({
					type : 'GET',
					url : 'getFeesByCourseId',
					data : {
						courseId : cId
					},
					success : function(resp) {
						$('#feesId').val(resp);
					}
				});
			}
		</script> -->

		<script>
		
		// Attach event listeners to perform real-time validation
	    document.getElementById("UserId").addEventListener("blur", validateUserId);
	    document.getElementById("passwordId").addEventListener("blur", validatePassword);
	    document.getElementById("repasswordId").addEventListener("blur", validateRepeatPassword);
	    document.getElementById("emailId").addEventListener("blur", validateEmail);
	    document.getElementById("MobileNoId").addEventListener("blur", validateMobileNo);
	    document.getElementById("dobId").addEventListener("blur", validateDateOfBirth);
	    document.getElementById("addressId").addEventListener("blur", validateAddress);
	    document.getElementById("genderId").addEventListener("blur", validategender);
	    document.querySelector('input[type="file"]').addEventListener('blur', validateImageOnBlur);
	    
	    // Functions to perform real-time validation
	    function validateUserId() {
	        var userId = document.getElementById("UserId").value;
	        if (userId.length < 6) {
	            alert("User Id must be at least 6 characters long.");
	            return false;
	        }
	    }

	    function validatePassword() {
	        var password = document.getElementById("passwordId").value;
	        var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#_.])[A-Za-z\d@#_.]{8,}$/;
	        
	        if (!passwordRegex.test(password)) {
	            alert("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special symbol (@, #, _, or .) with a minimum length of 8.");
	            return false;
	        }
	    
	    }

	    function validateRepeatPassword() {
	    	 var password = document.getElementById("passwordId").value;
	        var repeatPassword = document.getElementById("repasswordId").value;

	        if (password !== repeatPassword) {
	            alert("Password and Repeat Password must match.");
	            return false;
	        }
	    }

	    function validateEmail() {
	        var email = document.getElementById("emailId").value;
	        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	        var userEmail = "test@example.com";
	        
	        if (email.trim() === "") {
	            alert("Email cannot be empty.");
	            return false;
	        }else 
	            if (!emailRegex.test(email)) {
	                alert("Invalid email format.");
	                return false;
	            }
	    }

	    function validateMobileNo() {
	        var mobileNo = document.getElementById("MobileNoId").value;
	        if (mobileNo.trim() === "") {
	            alert("Mobile No cannot be empty.");
	            return false;
	        }
	    }

	    function validateDateOfBirth() {
	        var dob = document.getElementById("dobId").value;
	        if (dob.trim() === "") {
	            alert("Date of Birth cannot be empty.");
	            return false;
	        }
	    }

	    function validateAddress() {
	    	var address = document.getElementById("addressId").value;
	        if (address.trim() === "") {
	            alert("Address cannot be empty.");
	            return false;
	        }

	    }
	    
	    function validategender() {
	    	  var gender = document.querySelector('input[name="gender"]:checked');
	          if (!gender) {
	              alert("Please select a gender.");
	              return false;
	          }
	    }
	    function validateImageOnBlur() {
	        var fileInput = document.querySelector('input[type="file"]');
	        var fileName = fileInput.value;

	        if (fileName.trim() !== "") {
	            // Validate file type
	            var allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;
	            if (!allowedExtensions.exec(fileName)) {
	                alert("Invalid file type. Please upload a valid image file.");
	                fileInput.value = ""; // Clear the file input
	            }
	        }
	    }
	  

		        
		        /* // Validation for Full Name
		        var fullName = document.getElementById("fullNameId").value;
		        if (fullName.trim() === "") {
		            alert("Full Name cannot be empty.");
		            return false;
		        } */

		   
			/* function calculateCost() {
				var subId = $("#subscriptionId").val();
				var durationId = $("#durationId").val();

				$.ajax({
					type : 'GET',
					url : 'calculateCost',
					data : {
						subId : subId,
						duration : durationId
					},
					success : function(resp) {
						$('#costId').val(resp);
					},
					error : function() {
						console.log("Error in Ajax call");
					}
				});
			}

			function calculateAge() {
				// Get the input value from the date of birth field
				var dobInput = document.getElementById('dobId').value;

				// Convert the input value to a Date object
				var dob = new Date(dobInput);

				// Get the current date
				var currentDate = new Date();

				// Calculate the difference between the current date and the birthdate
				var ageInMillis = currentDate - dob;

				// Convert the age from milliseconds to years
				var ageInYears = Math.floor(ageInMillis
						/ (365.25 * 24 * 60 * 60 * 1000));

				// Display the result
				document.getElementById('age')
			} */
		</script>
	</div>
</body>

</html>
