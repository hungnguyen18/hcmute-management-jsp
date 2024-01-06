<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Objects"%>
<%@ page import="Bean.UserBean"%>
<%@ page import="Util.UserSessionUtil"%>
<%
UserBean user = UserSessionUtil.getUser(request);
%>

<layout:extends name="base">
	<layout:put block="contents">
		<div class="w-full flex justify-end">
			<button type="submit"
				class="bg-blue-500 text-white py-2 px-4 rounded-md"
				onClick="openModal('add', {serviceID: ''})">+ Add</button>
		</div>
		<!-- Service List -->
		<div>
			<h2 class="text-xl font-bold mb-4">Service List</h2>
			<!-- Your Service list table goes here -->

			<div id="table-scroll" class="table-scroll">
				<table id="main-table" class="main-table">
					<thead>
						<tr>
							<th scope="col">Service Code</th>
							<th scope="col">Service Name</th>
							<th scope="col">Description</th>
							<th scope="col">Responsible Department</th>
							<th scope="col">Processing Time</th>
							<th scope="col">Created by</th>

							<c:choose>
								<c:when test="${user.role == 'system_admin'}">
									<th scope="col">Action</th>
								</c:when>
							</c:choose>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="service" items="${services}">
							<tr>
								<th>${service.serviceCode}</th>
								<td>${service.serviceName}</td>
								<td>${service.description}</td>
								<td>${service.responsibleDepartment}</td>
								<td>${service.processingTime}</td>
								<td>${service.createdBy}</td>
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
				<h3 class="font-bold text-lg text-red-500">Add Service</h3>
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
				action="services" method="post">
				<input type="hidden" id="action" name="action" value="add">
				<input type="hidden" id="serviceID" name="serviceID"> <input
					type="hidden" id="serviceCode" name="serviceCode">
				<!-- Wrapper for Service Details -->
				<!-- Service Name Field -->
				<div>
					<label for="serviceName"
						class="block text-sm font-medium text-gray-700">Service
						Name</label> <input type="text" id="serviceName" name="serviceName"
						required placeholder="Example: CTSV Service"
						class="mt-1 p-2 border rounded-md w-full">
				</div>


				<!-- Description Field -->
				<div>
					<label for="description"
						class="block text-sm font-medium text-gray-700">Description</label>
					<textarea id="description" name="description"
						placeholder="Enter description"
						class="mt-1 p-2 border rounded-md w-full"></textarea>
				</div>

				<!-- Responsible Department Field -->
				<div>
					<label for="responsibleDepartment"
						class="block text-sm font-medium text-gray-700">Responsible
						Department</label> <input type="text" id="responsibleDepartment"
						name="responsibleDepartment" placeholder="Example: HR Department"
						class="mt-1 p-2 border rounded-md w-full">
				</div>

				<!-- Processing Time Field -->
				<div>
					<label for="processingTime"
						class="block text-sm font-medium text-gray-700">Processing
						Time (in days)</label> <input type="number" id="processingTime"
						name="processingTime" required placeholder="Example: 3"
						class="mt-1 p-2 border rounded-md w-full">
				</div>

				<!-- Created By Field -->
				<div>
					<label for="createdBy"
						class="block text-sm font-medium text-gray-700">Created By</label>

					<select id="createdBy" name="createdBy" required
						class="mt-1 p-2 border rounded-md w-full"></select>
				</div>

				<div class="col-span-2">
					<button type="submit"
						class="bg-blue-500 text-white py-2 px-4 rounded-md">Add</button>
				</div>
			</form>
		</div>
		</dialog>

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
			            value: item.ctsvStaffID,
			            text: item.staffName 
			        }));
			    });
			}
			
            function openModal(type, { serviceID }) {
            	$.get('ctsv?action=get', function(data) {
            		populateSelect('createdBy', data)
			    });
                // Define actions for different modal types
                const open = {
                    delete() {
                        document.getElementById('deleteModal').dataset.serviceID = serviceID;
                        // Open the modal
                        document.getElementById('deleteModal').showModal();
                    },
                    update() {
                        document.getElementById('editModal').showModal();
                        $('#action').val('update');
                        // Fetch Service details and populate the form
                        $.get('services?action=edit&serviceID=' + serviceID, function (data) {
                            $('#serviceCode').val(data.serviceCode);
                            $('#serviceName').val(data.serviceName);
                            $('#description').val(data.description);
                            $('#responsibleDepartment').val(data.responsibleDepartment);
                            $('#processingTime').val(data.processingTime);
                            $('#createdBy').val(data.createdBy);
                            $('#serviceID').val(data.serviceID);
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
                var serviceID = $('#deleteModal').data('serviceID');

                // Send a request to delete the Service
                $.get('services?action=delete&serviceID=' + serviceID, function (data) {
                    closeModal('deleteModal');
                    window.location.reload();
                });
            }
        </script>
	</layout:put>
</layout:extends>
