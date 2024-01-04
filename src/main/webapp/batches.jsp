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
				<h2 class="text-xl font-bold mb-4">Add Batch</h2>
				<!-- Your add major form goes here -->
				<form class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4"
					action="majors" method="post">
					<!-- Major Code Input -->
					<input type="hidden" name="action" value="add">
					<div>
						<label for="major_code"
							class="block text-sm font-medium text-gray-700">Batch
							Code</label> <input type="text" name="majorCode" placeholder="enter batch code..." required
							class="mt-1 p-2 border rounded-md w-full">
					</div>
					<!-- Major Name Input -->
					<div>
						<label for="major_name"
							class="block text-sm font-medium text-gray-700">Batch
							Name</label> <input type="text" name="majorName" placeholder="enter batch name..." required 
							class="mt-1 p-2 border rounded-md w-full">
					</div>
					<!-- Submit button -->
					<div class="col-span-3">
						<button type="submit"
							class="bg-blue-500 text-white py-2 px-4 rounded-md">Add
						</button>
					</div>
				</form>
			</div>

			<!-- Major List -->
			<!-- Major List -->
			<div>
				<h2 class="text-xl font-bold mb-4">Batch List</h2>
				<!-- Your major list table goes here -->

				<div id="table-scroll" class="table-scroll">
					<table id="main-table" class="main-table">
						<thead>
							<tr>
								<th scope="col">Batch code</th>
								<th scope="col">Batch name</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="major" items="${majors}">
								<tr>
									<td>${major.majorCode}</td>
									<td>${major.majorName}</td>
									<td>
										<button class="bg-yellow-500 text-white px-2 py-1 rounded-md"
											onclick="openModal('update',{majorID: '${major.majorID}', majorCode: '${major.majorCode}', majorName: '${major.majorName}'})">Edit</button>
										<button class="bg-red-500 text-white px-2 py-1 rounded-md"
											type="button"
											onclick="openModal('delete',{majorID: '${major.majorID}'})">Delete</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>


			</div>
		</div>


		<dialog id="deleteModal" class="modal">
		<div class="modal-box p-6 rounded-xl bg-white">
			<div class="flex justify-between items-center mb-4">
				<h3 class="font-bold text-lg text-red-500">Warning!</h3>
				<button onclick="closeModal('deleteModal')"
					class="text-gray-500 hover:text-red-500">
					<svg class="w-6 h-6" fill="none" stroke="currentColor"
						viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
          <path stroke-linecap="round" stroke-linejoin="round"
							stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
        </svg>
				</button>
			</div>
			<p class="py-4 text-gray-700">Are you sure you want to delete it?</p>
			<div class="modal-action flex justify-end">
				<button class="btn btn-outline mr-2"
					onclick="closeModal('deleteModal')">Close</button>

				<!-- if there is a button in form, it will close the modal -->

				<button class="bg-red-500 text-white px-4 py-2 rounded-md"
					onclick="confirmDelete()">Delete</button>

			</div>
		</div>
		</dialog>

		<dialog id="editModal" class="modal">
		<div class="modal-box p-6 rounded-xl bg-white w-[500px]">
			<div class="flex justify-between items-center mb-4">
				<h3 class="font-bold text-lg text-red-500">Edit Major</h3>
				<button onclick="closeModal('editModal')"
					class="text-gray-500 hover:text-red-500">
					<svg class="w-6 h-6" fill="none" stroke="currentColor"
						viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
          <path stroke-linecap="round" stroke-linejoin="round"
							stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
        </svg>
				</button>
			</div>

			<form id="editForm" class="grid grid-cols-1 md:grid-cols-2 gap-4"
				action="majors" method="post">
				<input type="hidden" name="action" value="update"> <input
					type="hidden" id="major_id" name="majorID">
				<div>
					<label for="edit_major_code"
						class="block text-sm font-medium text-gray-700">Major Code</label>
					<input type="text" id="major_code" name="majorCode" required
						class="mt-1 p-2 border rounded-md w-full">
				</div>
				<div>
					<label for="edit_major_name"
						class="block text-sm font-medium text-gray-700">Major Name</label>
					<input type="text" id="major_name" name="majorName" required
						class="mt-1 p-2 border rounded-md w-full">
				</div>
				<div class="col-span-3">
					<button type="submit"
						class="bg-blue-500 text-white py-2 px-4 rounded-md">Save
						Changes</button>
				</div>
			</form>
		</div>
		</dialog>



		<link rel="stylesheet" href="./resource/css/table.css">

		<script>
			function openModal(type, {majorID, majorCode, majorName}) {
				const open = {
						delete() {
							// Set major.code value in the modal
							document.getElementById('deleteModal').dataset.majorID = majorID;
							// Open the modal
							document.getElementById('deleteModal').showModal();
						},
						update() {
							document.getElementById('editModal').showModal();
							document.getElementById('major_id').value = majorID;
						    document.getElementById('major_code').value = majorCode;
						    document.getElementById('major_name').value = majorName;
						}
				}
				
				open[type]()
			}

			function closeModal(type) {
				document.getElementById(type).close();
			}
			
			function confirmDelete() {
			    // Retrieve major code from modal
			    var majorID = document.getElementById('deleteModal').dataset.majorID;
		
			    // Make an AJAX request using fetch
			    fetch('majors?action=delete&majorID='+ majorID, {
			      method: 'GET',
			      headers: {
			        'Content-Type': 'application/x-www-form-urlencoded',
			      },
			      
			    })
			    .then(response => {
			      if (!response.ok) {
			        throw new Error('Network response was not ok');
			      }
			      // Handle the response, if needed
			      // For example, you can update the UI or reload the page
			      closeModal('deleteModal');
			      window.location.reload()
			    })
			    .catch(error => {
			      console.error('There was a problem with the fetch operation:', error);
			    });
			  }
			
		</script>
	</layout:put>
</layout:extends>

