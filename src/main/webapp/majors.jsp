<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Objects"%>

<layout:extends name="base">
	<layout:put block="contents">
		<div class="container mt-8">

			<!-- Add Major Form -->
			<div class="mb-8">
				<h2 class="text-xl font-bold mb-4">Add Major</h2>
				<!-- Your add major form goes here -->
				<form class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4"
					action="majors" method="post">
					<!-- Major Code Input -->
					<input type="hidden" name="action" value="add">
					<div>
						<label for="major_code"
							class="block text-sm font-medium text-gray-700">Major
							Code</label> <input type="text" id="major_code" name="majorCode" required
							class="mt-1 p-2 border rounded-md w-full">
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
			<!-- Major List -->
			<div>
				<h2 class="text-xl font-bold mb-4">Major List</h2>
				<!-- Your major list table goes here -->

				<div id="table-scroll" class="table-scroll">
					<table id="main-table" class="main-table">
						<thead>
							<tr>
								<th scope="col">Major code</th>
								<th scope="col">Major name</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="major" items="${majors}">
								<tr>
									<td>${major.majorCode}</td>
									<td>${major.majorName}</td>
									<td>
										<button class="bg-yellow-500 text-white px-2 py-1 rounded-md">Edit</button>
										<button class="bg-red-500 text-white px-2 py-1 rounded-md">Delete</button>
									</td>
								</tr>
							</c:forEach>

						</tbody>

					</table>
				</div>


			</div>
		</div>

		<link rel="stylesheet" href="./resource/css/styles.css">
	</layout:put>
</layout:extends>

