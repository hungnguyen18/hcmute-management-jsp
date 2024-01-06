<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Objects"%>
<%@ page import="Dao.*"%>
<%@ page import="Bean.UserBean"%>
<%@ page import="Util.UserSessionUtil"%>
<%UserBean user = UserSessionUtil.getUser(request);%>

<layout:extends name="base">
	<layout:put block="contents">

		<c:choose>
			<c:when test="${user.role == 'system_admin' || user.role == 'student'}">
				<div class="w-full flex justify-end">
					<button type="submit"
						class="bg-blue-500 text-white py-2 px-4 rounded-md"
						onClick="openModal('add', {serviceID: ''})">+ Add</button>
				</div>
			</c:when>
		</c:choose>
		<!-- Request List -->
		<div>
			<h2 class="text-xl font-bold mb-4">Request List</h2>
			<!-- Your Request list table goes here -->

			<div id="table-scroll" class="table-scroll">
				<table id="main-table" class="main-table">
					<thead>
						<tr>
							<th scope="col">Request Code</th>
							<th scope="col">Service ID</th>
							<th scope="col">Submission Date</th>
							<th scope="col">Expiration Time</th>
							<th scope="col">Status</th>
							<th scope="col">Request Text</th>
							<c:choose>
								<c:when
									test="${user.role == 'system_admin' || user.role == 'ctsv_staff'}">
									<th scope="col">Action</th>
								</c:when>
							</c:choose>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="request" items="${requests}">
							<tr>
								<td>${request.requestCode}</td>
								<td>${request.serviceID}</td>
								<td>${request.submissionDate}</td>
								<td>${request.expirationTime}</td>
								<td>${request.status}</td>
								<td>${request.requestText}</td>
								<c:choose>
									<c:when test="${user.role == 'system_admin'}">
										<td>
											<button class="bg-yellow-500 text-white px-2 py-1 rounded-md"
												onclick="openModal('update',{serviceID: '${service.serviceID}'})">Edit</button>
											<button class="bg-red-500 text-white px-2 py-1 rounded-md"
												type="button"
												onclick="openModal('delete',{serviceID: '${service.serviceID}'})">Delete</button>
										</td>
									</c:when>
									<c:when test="${user.role == 'ctsv_staff'}">
										<td>
											<button class="bg-yellow-500 text-white px-2 py-1 rounded-md"
												onclick="openModal('handle',{ requestID: '${request.requestID}', studentID: '${ request.studentID}'})">Handle</button>
										</td>
									</c:when>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!-- Delete Modal -->
		<dialog id="deleteModal" class="modal">
		<div class="modal-box p-6 rounded-xl bg-white">
			<div class="flex justify-between items-center mb-4">
				<h3 class="font-bold text-lg text-red-500">Warning!</h3>
				<button onclick="closeModal('deleteModal')"
					class="text-gray-500 hover:text-red-500">
					<svg class="w-6 h-6" fill="none" stroke="currentColor"
						viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path stroke-linecap="round"
							stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
				</button>
			</div>
			<p class="py-4 text-gray-700">Are you sure you want to delete it?</p>
			<div class="modal-action flex justify-end">
				<button class="btn btn-outline mr-2"
					onclick="closeModal('deleteModal')">Close</button>
				<button class="bg-red-500 text-white px-4 py-2 rounded-md"
					onclick="confirmDelete()">Delete</button>
			</div>
		</div>
		</dialog>

		<!-- Edit Modal -->
		<dialog id="editModal" class="modal">
		<div class="modal-box p-6 rounded-xl bg-white w-[500px]">
			<div class="flex justify-between items-center mb-4">
				<h3 class="font-bold text-lg text-red-500">Edit Request</h3>
				<button onclick="closeModal('editModal')"
					class="text-gray-500 hover:text-red-500">
					<svg class="w-6 h-6" fill="none" stroke="currentColor"
						viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path stroke-linecap="round"
							stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
				</button>
			</div>

			<form id="addRequestForm"
				class="grid grid-cols-1 md:grid-cols-2 gap-4" action="requests"
				method="post">
				<input type="hidden" id="action" name="action" value="add">
				<input type="hidden" id="requestID" name="requestID"> <input
					type="hidden" id="studentID" name="studentID">

				<!-- Service ID Field -->
				<div>
					<label for="serviceID"
						class="block text-sm font-medium text-gray-700">Service ID</label>
					<select id="serviceID" name="serviceID" required
						class="mt-1 p-2 border rounded-md w-full"></select>
				</div>

				<!-- Request Text Field -->
				<div>
					<label for="requestText"
						class="block text-sm font-medium text-gray-700">Request
						Text</label>
					<textarea id="requestText" name="requestText" required
						placeholder="Enter request details"
						class="mt-1 p-2 border rounded-md w-full"></textarea>
				</div>

				<div class="col-span-2">
					<button type="submit"
						class="bg-blue-500 text-white py-2 px-4 rounded-md">Add
						Request</button>
				</div>
			</form>
		</div>
		</dialog>
		
		<!-- Edit Modal -->
		<dialog id="handleModal" class="modal">
		<div class="modal-box p-6 rounded-xl bg-white w-[500px]">
			<div class="flex justify-between items-center mb-4">
				<h3 class="font-bold text-lg text-red-500">Handle Request</h3>
				<button onclick="closeModal('handleModal')"
					class="text-gray-500 hover:text-red-500">
					<svg class="w-6 h-6" fill="none" stroke="currentColor"
						viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path stroke-linecap="round"
							stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
				</button>
			</div>

			<form id="addRequestForm"
				class="grid grid-cols-1 md:grid-cols-2 gap-4" action="feedbacks"
				method="post">
				<input type="hidden" id="action" name="action" value="add">
				<input type="hidden" id="requestID2" name="requestID"> <input
					type="hidden" id="studentID2" name="studentID">

				<!-- Request Text Field -->
				<div>
					<label for="requestText"
						class="block text-sm font-medium text-gray-700">Feedback
					</label>
					<textarea id="requestText" name="feedbackText" required
						placeholder="Enter feedback details"
						class="mt-1 p-2 border rounded-md w-full"></textarea>
				</div>

				<div class="col-span-2">
					<button type="submit"
						class="bg-blue-500 text-white py-2 px-4 rounded-md">Handle
						Request</button>
				</div>
			</form>
		</div>
		</dialog>

		<!-- Your style and script tags go here -->
		<!-- Your style and script tags go here -->
		<style>
.table-scroll {
	position: relative;
	width: 100%;
	z-index: 1;
	margin: auto;
	overflow: auto;
	height: 70vh;
}

.table-scroll table {
	width: 100%;
	margin: auto;
	border-collapse: separate;
	border-spacing: 0;
}

.table-wrap {
	position: relative;
}

.table-scroll th, .table-scroll td {
	padding: 5px 10px;
	border: 1px solid #f8f8f8;
	background: #fff;
	vertical-align: top;
}

.table-scroll thead th {
	background: lightseagreen;
	color: #fff;
	position: -webkit-sticky;
	position: sticky;
	top: 0;
}

/* safari and ios need the tfoot itself to be position:sticky also */
.table-scroll tfoot, .table-scroll tfoot th, .table-scroll tfoot td {
	position: -webkit-sticky;
	position: sticky;
	bottom: 0;
	background: midnightblue;
	color: #fff;
	z-index: 4;
}

a:focus {
	background: red;
}

/* testing links*/
th:first-child {
	position: -webkit-sticky;
	position: sticky;
	left: 0;
	z-index: 2;
	background: darkslategray;
	color: #fff;
}

thead th:first-child, tfoot th:first-child {
	z-index: 5;
}
</style>

		<script>
		
			function populateSelect(selectId, data) {
			    var select = $('#' + selectId);
		
			    select.empty();
		
			    $.each(data, function(index, item) {
			        select.append($('<option>', {
			            value: item.serviceID,
			            text: item.serviceName 
			        }));
			    });
			}
			
            function openModal(type, { requestID, studentID }) {
            	
            
                // Define actions for different modal types
                const open = {
                    delete() {
            
                        // Open the modal
                        document.getElementById('deleteModal').showModal();
                    },
                    update() {
                        document.getElementById('editModal').showModal();
                        $('#action').val('update');
````
                        // Fetch Service details and populate the form
                       /*  $.get('services?action=edit&serviceID=' + serviceID, function (data) {
                            $('#serviceCode').val(data.serviceCode);
                            $('#serviceName').val(data.serviceName);
                            $('#description').val(data.description);
                            $('#responsibleDepartment').val(data.responsibleDepartment);
                            $('#processingTime').val(data.processingTime);
                            $('#createdBy').val(data.createdBy);
                            $('#serviceID').val(data.serviceID);
                        }); */
                    },
                    add() {
                    	var userId = "${user.user_ID}"
                     	$.get('students?action=get&userId='+ userId, function(data) {
                     		 $('#studentID').val(data.studentID);
        			    });
                     	$.get('services?action=get', function(data) {
                     		populateSelect('serviceID', data)
       			    	});
                     	$.get('ctsv?action=get', function(data) {
                    		populateSelect('createdBy', data)
        			    });
                        document.getElementById('editModal').showModal();
                    },
                    handle() {
                    	 document.getElementById('handleModal').showModal();
                    	 console.log(requestID, studentID)
                    	 $('#requestID2').val(requestID);
                         $('#studentID2').val(studentID);
                    }
                };

                open[type]();
            }

            function closeModal(type) {
                document.getElementById(type).close();
            }

            function confirmDelete() {
                var serviceID = $('#deleteModal').data('serviceID');

                // Send a request to delete the Service
                $.get('services?action=delete&serviceID=' + serviceID, function (data) {
                    closeModal('deleteModal');
                    window.location.reload();
                });
            }
        </script>
		<!-- Include any necessary CSS and JavaScript for modals and table scrolling -->
	</layout:put>
</layout:extends>
