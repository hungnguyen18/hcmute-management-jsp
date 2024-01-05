
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Objects"%>

<layout:extends name="base">
	<layout:put block="contents">
		<div class="w-full flex justify-end">
			<button type="submit"
				class="bg-blue-500 text-white py-2 px-4 rounded-md"
				onClick="openModal('add', {studentID: ''})">+ Add</button>
		</div>
		<!-- Major List -->
		<div>
			<h2 class="text-xl font-bold mb-4">Student List</h2>
			<!-- Your student list table goes here -->

			<div id="table-scroll" class="table-scroll">
				<table id="main-table" class="main-table">
					<thead>
						<tr>
							<th scope="col">MSSV</th>
							<th scope="col">Student Name</th>
							<th scope="col">Birthdate</th>
							<th scope="col">Gender</th>
							<th scope="col">Department</th>
							<th scope="col">Major ID</th>
							<th scope="col">Batch ID</th>
							<th scope="col">Education System</th>
							<th scope="col">Email</th>
							<th scope="col">Phone Number</th>
							<th scope="col">Address</th>
							<th scope="col" class="w-64">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="student" items="${students}">
							<tr>
								<th>${student.mssv}</th>
								<td>${student.studentName}</td>
								<td>${student.birthdate}</td>
								<td>${student.gender}</td>
								<td>${student.department}</td>
								<td>${student.majorID}</td>
								<td>${student.batchID}</td>
								<td>${student.educationSystem}</td>
								<td>${student.email}</td>
								<td>${student.phoneNumber}</td>
								<td>${student.address}</td>
								<td>
									<button class="bg-yellow-500 text-white px-2 py-1 rounded-md"
										onclick="openModal('update',{studentID: '${student.studentID}'})">Edit</button>
									<button class="bg-red-500 text-white px-2 py-1 rounded-md"
										type="button"
										onclick="openModal('delete',{studentID: '${student.studentID}'})">Delete</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
				<h3 class="font-bold text-lg text-red-500">Add Student</h3>
				<button onclick="closeModal('editModal')"
					class="text-gray-500 hover:text-red-500">
					<svg class="w-6 h-6" fill="none" stroke="currentColor"
						viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
	                    <path stroke-linecap="round"
							stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
	                </svg>
				</button>
			</div>

			<form id="editForm" class="grid grid-cols-1 md:grid-cols-2 gap-4"
				action="students" method="post">
				<input type="hidden" id="action" name="action" value="add">
				<input type="hidden" id="student_id" name="studentID">
				<input type="hidden" id="user_id" name="userID">
				<!-- Wrapper for Username and Password Fields -->
				<div
					class="row-span-2 grid grid-cols-1 grid-rows-2 gap-4 border border-red-500 rounded-md p-4 mb-4">
					<!-- Username Field -->
					<div>
						<label for="edit_username"
							class="block text-sm font-medium text-gray-700 text-red-500">Username</label>
						<input type="text" id="username" name="username" required
							placeholder="Example: john_doe"
							class="mt-1 p-2 border rounded-md w-full">
					</div>

					<!-- Password Field -->
					<div>
						<label for="edit_password"
							class="block text-sm font-medium text-gray-700 text-red-500">Password</label>
						<input type="text" id="password" name="password" required
							placeholder="Enter password"
							class="mt-1 p-2 border rounded-md w-full">
					</div>
				</div>

				<div>
					<label for="edit_student_name"
						class="block text-sm font-medium text-gray-700">Name</label> <input
						type="text" id="student_name" name="studentName" required
						placeholder="Example: John Doe"
						class="mt-1 p-2 border rounded-md w-full">
				</div>

				<div>
					<label for="edit_birthdate"
						class="block text-sm font-medium text-gray-700">Birthdate</label>
					<input type="date" id="birthdate" name="birthdate"
						placeholder="Select birthdate"
						class="mt-1 p-2 border rounded-md w-full">
				</div>

				<div>
					<label for="edit_gender"
						class="block text-sm font-medium text-gray-700">Gender</label> <select
						id="gender" name="gender"
						class="mt-1 p-2 border rounded-md w-full">
						<option value="Male">Male</option>
						<option value="Female">Female</option>
					</select>
				</div>

				<div>
					<label for="edit_department"
						class="block text-sm font-medium text-gray-700">Department</label>
					<input type="text" id="department" name="department"
						placeholder="Example: Computer Science"
						class="mt-1 p-2 border rounded-md w-full">
				</div>

				<!-- Trường chọn Major -->
				<div>
					<label for="edit_major_name"
						class="block text-sm font-medium text-gray-700">Major</label> <select
						id="major_id" name="majorID"
						class="mt-1 p-2 border rounded-md w-full">
					</select>
				</div>

				<!-- Trường chọn Batch -->
				<div>
					<label for="edit_batch_name"
						class="block text-sm font-medium text-gray-700">Batch</label> <select
						id="batch_id" name="batchID"
						class="mt-1 p-2 border rounded-md w-full">

					</select>
				</div>

				<div>
					<label for="edit_education_system"
						class="block text-sm font-medium text-gray-700">Education
						System</label> <input type="text" id="education_system"
						name="educationSystem" placeholder="Example: Full-time"
						class="mt-1 p-2 border rounded-md w-full">
				</div>

				<div>
					<label for="edit_email"
						class="block text-sm font-medium text-gray-700">Email</label> <input
						type="email" id="email" name="email"
						placeholder="Example: john.doe@example.com"
						class="mt-1 p-2 border rounded-md w-full">
				</div>

				<div>
					<label for="edit_phone_number"
						class="block text-sm font-medium text-gray-700">Phone
						Number</label> <input type="text" id="phone_number" name="phoneNumber"
						placeholder="Example: +123456789"
						class="mt-1 p-2 border rounded-md w-full">
				</div>

				<div>
					<label for="edit_address"
						class="block text-sm font-medium text-gray-700">Address</label>
					<textarea id="address" name="address" rows="3"
						placeholder="Example: 123 Main St, City"
						class="mt-1 p-2 border rounded-md w-full"></textarea>
				</div>

				<div class="col-span-2">
					<button type="submit"
						class="bg-blue-500 text-white py-2 px-4 rounded-md">Add</button>
				</div>
			</form>
		</div>
		</dialog>


		<link rel="stylesheet" href="./resource/css/table.css">

		<script>
			function populateSelect(selectId, data) {
			    var select = $('#' + selectId);

			    // Xóa tất cả các tùy chọn hiện tại
			    select.empty();

			    // Thêm tùy chọn mặc định nếu cần
			    // select.append($('<option>', {
			    //     value: '',
			    //     text: 'Select an option'
			    // }));

			    // Thêm các tùy chọn từ dữ liệu
			    $.each(data, function(index, item) {
			        select.append($('<option>', {
			            value: item.majorID || item.batchID,
			            text: item.majorName || item.batchName
			        }));
			    });
			}

			function openModal(type, { studentID }) {
			    $.get('majors?action=get', function(data) {
			        populateSelect('major_id', data);
			    });
			    $.get('batches?action=get', function(data) {
			        populateSelect('batch_id', data);
			    });

			    const open = {
			        delete() {
			            // Set major.code value in the modal
			            // document.getElementById('deleteModal').dataset.majorID = majorID;
			            // Open the modal
			        	document.getElementById('deleteModal').showModal();
			        },
			        update() {
			        	document.getElementById('editModal').showModal();
			            $('#action').val('update');

			            $.get('students?action=edit&studentID=' + studentID, function(data) {
			                $('#username').val(data.username);
			                $('#password').val(data.password);
			                $('#student_name').val(data.studentName);
			                $('#birthdate').val(data.birthdate);
			                $('#gender').val(data.gender);
			                $('#department').val(data.department);
			                $('#major_id').val(data.majorID);
			                $('#batch_id').val(data.batchID);
			                $('#education_system').val(data.educationSystem);
			                $('#email').val(data.email);
			                $('#phone_number').val(data.phoneNumber);
			                $('#address').val(data.address);
			                $('#student_id').val(data.studentID);
			                $('#user_id').val(data.userID);
			            });
			        },
			        add() {
			        	document.getElementById('editModal').showModal();
			        }
			    };

			    open[type]();
			}

			function closeModal(type) {
				document.getElementById(type).close();
			}

			function confirmDelete() {
			    var majorID = $('#deleteModal').data('majorID');

			    // Make an AJAX request using jQuery
			    $.ajax({
			        url: 'majors?action=delete&majorID=' + majorID,
			        method: 'GET',
			        success: function(response) {
			            closeModal('deleteModal');
			            window.location.reload();
			        },
			        error: function(error) {
			            console.error('There was a problem with the AJAX request:', error);
			        }
			    });
			}

				
			</script>
	</layout:put>
</layout:extends>

