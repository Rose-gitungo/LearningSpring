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

		<div class="h2 text-center text-warning">Sports Club
			Registration Form</div>
		<div class="card">

			<div class="card-body">
				<form action="./saveSports" method="post"
					enctype="multipart/form-data" onsubmit="validateForm()">
					<div class="container">
						<div class="row mt-3">
							<div class="col-5">
							<input type="hidden" name="registrationid" value="${registrationid}">
								<label for="clubnameId" class="font-weight-bold">Club
									Name</label> <select name="clubName" id="clubnameId"
									class="form-control">
									<option value="0">-select-</option>
									<c:forEach items="${clubList}" var="club">
										<option value="${club.clubid}">${club.clubname}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-5">
								<label for="sportNameId" class="font-weight-bold">Sports
									Name</label> <select name="sportName" id="sportNameId"
									class="form-control">
									<option value="0">-select-</option>
									<c:forEach items="${sportsList}" var="sports">
										<option value="${sports.sportsid}">${sports.sportsname}</option>
									</c:forEach>

								</select>
							</div>
						</div>
						<hr>

						<div class="row mt-2">
							<div class="col-4">
								<label for="applicantNameId" class="font-weight-bold">Applicant
									Name*</label> <input type="text" class="form-control" name="name"
									id="applicantNameId" maxlength="50">
							</div>
							<div class="col-4">
								<label for="emailId" class="font-weight-bold">Email*</label> <input
									type="text" class="form-control" name="email" id="emailId"
									maxlength="50">
							</div>

							<div class="col-4">
								<label for="mobileNoId" class="font-weight-bold">Mobile
									No*</label> <input type="text" class="form-control" name="mobileNo"
									id="mobileNoId">
							</div>
						</div>
						<div class="row mt-3">
							<!-- <div class="col-4">
								<label for="dobId" class="font-weight-bold">Date of
									Birth</label> <input type="date" class="form-control" name="dob"
									id="dobId">
							</div> -->
							<div class="col-4">
								<label class="font-weight-bold">Gender</label>
								<div>
									<input type="radio" name="gender" id="male" value="male">
									Male &nbsp; &nbsp; &nbsp; <input type="radio" name="gender"
										id="female" value="female"> Female
								</div>
							</div>
							<div class="col-4">
								<label class="font-weight-bold">Upload Photo</label> <input
									type="file" class="form-control" name="photo">

							</div>
						</div>
					</div>

					<div class="mt-2 text-center">
						<input type="submit" class="btn btn-success"> <input
							type="reset" class="btn btn-warning">
					</div>

				</form>
			</div>
			<div class="card-footer">
			<form action="./filter" method="post">
			
				<div class="container">
					<div class="text-primary  text-center h2">Filter Registration
						Details</div>
					<div class="row mt-2">
						<div class="col-5">
							<label for="clubNameId" class="font-weight-bold">Club
								Name</label> <select name="clubName" id="clubNameId"
								class="form-control">
								<option value="0">-select-</option>
								<c:forEach items="${clubList}" var="club">
									<option value="${club.clubid}">${club.clubname}</option>
								</c:forEach>

							</select>
						</div>
					<div>
					
					</div>
							<input type="submit" class="btn btn-success" value="Search">
						
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<div class="mt-2">
		<div class="h3">Registration Details</div>

		<table class="table table-bordered table-striped mt-2">
			<thead>
				<tr>
					<th>Sl.#</th>
					<th>Name</th>
					<th>Email</th>
					<th>Mobile No</th>
					<th>Image Path</th>
					<th>club Name</th>
					<th>Sports Name</th>
					<th>Fees</th>
				</tr>

			</thead>

			<tbody>
				<c:forEach items="${regList}" var="reg" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${reg.applicantname}</td>
						<td>${reg.email}</td>
						<td>${reg.mobileno }
						<%-- <fmt:formatDate pattern="yyyy-MM-dd" value="${st.dob}" /> --%>
						</td>
						<td>${reg.imagepath}</td>
						<td>${reg.clubMaster.clubname}</td>
						<td>${reg.sportsMaster.sportsname}</td>
						<td>${reg.sportsMaster.fees}</td>
	
					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>
	<script>

	function validateForm() {
		console.log("yes");

		var sId = $('#clubnameId').val();
		var cId = $('#sportNameId').val();
		var fnameId = $('#applicantNameId').val();
		var fathNameId = $('#emailId').val();
		var adharId = $('#mobileNoId').val();
		var addId = $('#dobId').val();
		var fcId = $('input[name="gender"]:checked').val();
		var status = true;
		if (sId == 0) {
			alert("Plz select a club Name !!!");
			status = false;
		}
		if (cId == 0) {
			alert("Plz select a Sports Name !!!");
			status = false;
		}
		if (fnameId == "") {
			alert("Plz provide Applicant name");
			status = false;
		}
		if (fathNameId == "") {
			alert("Plz provide Email name");
			status = false;
		}
		if (adharId == "") {
			alert("Plz provide Mobile No");
			status = false;
		}
		if (addId == "") {
			alert("Plz provide dob");
			status = false;
		}
		if (!fcId) {
			alert("Plz select Gender");
			status = false;
		}

		return status;
	}
	
		  $(document).ready(function() {
		        $("#clubnameId").change(function() {
		            var clubid = $(this).val();
		            console.log(clubid)
		            if (clubid != 0) {
		                $.ajax({
		                    url: "getSportsForClub", // Replace with your servlet URL
		                    type: "GET",
		                    data: { clubid: clubid },
		                    success: function(sportsList) {
		                    	 $("#sportNameId").empty();
		                    	    $.each(sportsList, function(index, sport) {
		                    	        $("#sportNameId").append(
		                    	            $('<option>').val(sport.sportsid).text(sport.sportsname)
		                    	        );
		                    	    });
		                    }
		                });
		            } else {
		                $("#sportNameId").empty().append(
		                    $('<option>').val(0).text("-select-")
		                );
		            }
		        });
		    });
	</script>
</body>

</html>
