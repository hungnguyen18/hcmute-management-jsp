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
                onClick="openModal('add', {ctsvStaffID: ''})">+ Add</button>
        </div>
        <!-- CTSV Staff List -->
        <div>
            <h2 class="text-xl font-bold mb-4">CTSV Staff List</h2>
            <!-- Your CTSV Staff list table goes here -->

            <div id="table-scroll" class="table-scroll">
                <table id="main-table" class="main-table">
                    <thead>
                        <tr>
                            <th scope="col">CTSV Staff ID</th>
                            <th scope="col">Staff Name</th>
                            <th scope="col">Email</th>
                            <th scope="col" >Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ctsvStaff" items="${ctsvStaffs}">
                            <tr>
                                <th>${ctsvStaff.ctsvStaffID}</th>
                                <td>${ctsvStaff.staffName}</td>
                                <td>${ctsvStaff.email}</td>
                                <td>
                                    <button class="bg-yellow-500 text-white px-2 py-1 rounded-md"
                                        onclick="openModal('update',{ctsvStaffID: '${ctsvStaff.ctsvStaffID}'})">Edit</button>
                                    <button class="bg-red-500 text-white px-2 py-1 rounded-md"
                                        type="button"
                                        onclick="openModal('delete',{ctsvStaffID: '${ctsvStaff.ctsvStaffID}'})">Delete</button>
                                </td>
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
                            <path stroke-linecap="round" stroke-linejoin="round"
                                stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
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
                    <h3 class="font-bold text-lg text-red-500">Add CTSV Staff</h3>
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
                    action="ctsv" method="post">
                    <input type="hidden" id="action" name="action" value="add">
                    <input type="hidden" id="ctsv_staff_id" name="ctsvStaffID">
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
                        <label for="edit_staff_name"
                            class="block text-sm font-medium text-gray-700">Name</label> <input
                            type="text" id="staff_name" name="staffName" required
                            placeholder="Example: John Doe"
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
                            class="block text-sm font-medium text-gray-700">Phone Number</label> <input
                            type="text" id="phone_number" name="phoneNumber"
                            placeholder="Example: +84373002409"
                            class="mt-1 p-2 border rounded-md w-full">
                    </div>

                    <div class="col-span-2">
                        <button type="submit"
                            class="bg-blue-500 text-white py-2 px-4 rounded-md">Add</button>
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

            .table-scroll th,
            .table-scroll td {
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
            .table-scroll tfoot,
            .table-scroll tfoot th,
            .table-scroll tfoot td {
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

            thead th:first-child,
            tfoot th:first-child {
                z-index: 5;
            }
        </style>

        <script>
            function openModal(type, { ctsvStaffID }) {
            

                // Define actions for different modal types
                const open = {
                    delete() {
                        document.getElementById('deleteModal').dataset.ctsvStaffID = ctsvStaffID;
                        // Open the modal
                        document.getElementById('deleteModal').showModal();
                    },
                    update() {
                        document.getElementById('editModal').showModal();
                        $('#action').val('update');

                        // Fetch CTSV Staff details and populate the form
                        $.get('ctsv?action=edit&ctsvID=' + ctsvStaffID, function (data) {
                            $('#username').val(data.username);
                            $('#password').val(data.password);
                            $('#staff_name').val(data.staffName);
                            $('#email').val(data.email);
                            $('#role').val(data.role);
                            $('#ctsv_staff_id').val(data.ctsvStaffID);
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
                var ctsvStaffID = $('#deleteModal').data('ctsvStaffID');

                // Send a request to delete the CTSV Staff
                $.get('ctsv?action=delete&ctsvStaffID=' + ctsvStaffID, function (data) {
                    closeModal('deleteModal');
                    window.location.reload();
                });
            }
        </script>
  

    </layout:put>
</layout:extends>
