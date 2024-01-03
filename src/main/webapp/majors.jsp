<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Objects"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<script>
        const successMessage = '<%=Objects.toString(request.getAttribute("successMessage"), "")%>
		';
		if (successMessage !== "") {
			alert(successMessage);
		}
	</script>
	<layout:extends name="base">
		<layout:put block="contents">
			<div class="container mt-8">

				<!-- Add Major Form -->
				<div class="mb-8">
					<h2 class="text-xl font-bold mb-4">Add Major</h2>
					<!-- Your add major form goes here -->
					<form class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4"
						action="MajorController" method="post">
						<!-- Major Code Input -->
						<input type="hidden" name="action" value="add">
						<div>
							<label for="major_code"
								class="block text-sm font-medium text-gray-700">Major
								Code</label> <input type="text" id="major_code" name="majorCode"
								required class="mt-1 p-2 border rounded-md w-full">
						</div>
						<!-- Major Name Input -->
						<div>
							<label for="major_name"
								class="block text-sm font-medium text-gray-700">Major
								Name</label> <input type="text" name="majorName" required
								class="mt-1 p-2 border rounded-md w-full">
						</div>
						<!-- Submit button -->
						<div class="col-span-3">
							<button type="submit"
								class="bg-blue-500 text-white py-2 px-4 rounded-md">Add
								Major</button>
						</div>
					</form>
				</div>

				<!-- Major List -->
				<div>
					<h2 class="text-xl font-bold mb-4">Major List</h2>
					<!-- Your major list table goes here -->

					<table class="text-left w-full">
						<thead class="bg-black flex text-white w-full">
							<tr class="flex w-full">
								<th class="p-4 w-1/3">Major code</th>
								<th class="p-4 w-1/3">Major name</th>
								<th class="p-4 w-1/3">Action</th>

							</tr>
						</thead>
						<!-- Remove the nasty inline CSS fixed height on production and replace it with a CSS class — this is just for demonstration purposes! -->
						<tbody
							class="bg-grey-light flex flex-col items-center justify-between overflow-y-scroll w-full"
							style="height: 50vh;">
							
							<c:forEach var="major" items="${majors}">
								<tr>
									<td class="p-4 w-1/3">${major.majorCode}}</td>
									<td class="p-4 w-1/3">${major.majorName}</td>
									<td class="p-4 w-1/3">
										<button class="bg-yellow-500 text-white px-2 py-1 rounded-md">Edit</button>
										<button class="bg-red-500 text-white px-2 py-1 rounded-md">Delete</button>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

				</div>
			</div>
		</layout:put>
	</layout:extends>
</body>
</html>