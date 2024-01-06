<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Bean.UserBean"%>
<%@ page import="Util.UserSessionUtil"%>


<%
UserBean user = UserSessionUtil.getUser(request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="../../resource/css/styles.css">

</head>
<body>

	<div id="wrapper" class="flex justify-stretch ">
		<layout:block name="sidebar">
			<aside class="w-64 bg-[#152259] h-screen text-white divide-y">
				<!-- Logo Section -->
				<div class="h-24 py-4 px-6 flex items-center justify-center">
					<!-- Your Logo Goes Here -->
					<p class="text-7xl font-black drop-shadow-2xl">UTE</p>
				</div>
				<!-- Navigation Section -->
				<nav>
					<ul class="mt-4">
						<li class="py-2 px-2"><a href="dashboard.jsp"
							class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
								Dashboard </a></li>
						<c:choose>
							<c:when test="${user.role == 'student'}">
								<!-- Sidebar menu for student -->
								<li class="py-2 px-2"><a href="services"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Services </a></li>
								<li class="py-2 px-2"><a href="requests"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Request </a></li>
								<!-- Add more services for student as needed -->
							</c:when>
							<c:when test="${user.role == 'ctsv_staff'}">
								<!-- Sidebar menu for CTSV staff -->
								<li class="py-2 px-2"><a href="requests"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Request </a></li>
								<!-- Add more services for CTSV staff as needed -->
							</c:when>
							<c:when test="${user.role == 'system_admin'}">
								<!-- Sidebar menu for system admin -->
								<li class="py-2 px-2"><a href="students"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Students </a></li>
								<li class="py-2 px-2"><a href="ctsv"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										CTSV staff </a></li>
								<li class="py-2 px-2"><a href="majors"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Majors </a></li>
								<li class="py-2 px-2"><a href="batches"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Batches </a></li>
								<li class="py-2 px-2"><a href="services"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Services </a></li>
								<li class="py-2 px-2"><a href="requests"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Requests </a></li>
								
								<!-- Add more management options for system admin as needed -->
							</c:when>
						</c:choose>
					</ul>
				</nav>
			</aside>
		</layout:block>

		<div id="content" class="w-screen ">
			<layout:block name="header">

				<header class="bg-gray-100 p-4">
					<div class="container mx-auto flex justify-end items-center">
						<div class="flex items-center space-x-4">
							<div class="relative">
								<button id="notificationButton" class="focus:outline-none">
									<svg class="h-6 w-6 text-black" fill="none"
										stroke="currentColor" viewBox="0 0 24 24"
										xmlns="http://www.w3.org/2000/svg">
              <path stroke-linecap="round" stroke-linejoin="round"
											stroke-width="2"
											d="M12 19l9 2-9-18-9 18 9-2zm0 0v6m0 0s4 0 4.5 4H7c.5-4 4.5-4 4.5-4z"></path>
            </svg>
									<span id="notificationBadge"
										class="absolute top-0 right-0 bg-red-500 text-white rounded-full p-1 text-xs cursor-pointer">3</span>
								</button>
								<div id="notificationMenu"
									class="hidden absolute right-0 mt-2 bg-white p-4 rounded shadow origin-top-left z-10 w-60">
									<!-- Your notification items go here -->
									<a href="#"
										class="block px-6 py-2 text-gray-800 hover:bg-gray-200">Notification 1</a> <a href="#"
										class="block px-6 py-2 text-gray-800 hover:bg-gray-200">Notification 2</a> <a href="#"
										class="block px-6 py-2 text-gray-800 hover:bg-gray-200">Notification 3</a>
								</div>
							</div>
							<div class="relative">
								<span>${user.username}(${user.role})</span>
							</div>
							<div class="relative">

								<button id="avatarButton" class="focus:outline-none">
									<img src="${pageContext.request.contextPath}/assets/avatar.png"
										alt="Avatar"
										class="w-8 h-8 rounded-full border-2 border-sky-500">
								</button>
								<div id="avatarMenu"
									class="hidden absolute right-0 mt-2 bg-white p-2 rounded shadow origin-top-left z-10">
									<!-- Your menu items go here -->
									<a href="profile.jsp"
										class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Profile</a>
									<a href="#"
										class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Settings</a>
									<a href="signin.jsp"
										class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Logout</a>
								</div>
							</div>
						</div>
					</div>
				</header>

				<!-- Your existing HTML code -->

				<script type="module">
  // Toggle notification menu visibility
  const notificationBadge = document.getElementById('notificationBadge');
  const notificationMenu = document.getElementById('notificationMenu');

  // Toggle avatar menu visibility
  const avatarButton = document.getElementById('avatarButton');
  const avatarMenu = document.getElementById('avatarMenu');

  document.addEventListener('click', (event) => {
    const isNotificationClickInside = notificationMenu.contains(event.target) || notificationBadge.contains(event.target);
    const isAvatarClickInside = avatarMenu.contains(event.target) || avatarButton.contains(event.target);

    if (!isNotificationClickInside) {
      notificationMenu.classList.add('hidden');
    }

    if (!isAvatarClickInside) {
      avatarMenu.classList.add('hidden');
    }
  });

  notificationBadge.addEventListener('click', () => {
    notificationMenu.classList.toggle('hidden');
    // Reset notification count to zero when menu is opened
    notificationBadge.textContent = '0';
  });

  avatarButton.addEventListener('click', () => {
    avatarMenu.classList.toggle('hidden');
  });
</script>



			</layout:block>
			<div class="container mx-auto p-4">
				<layout:block name="contents">
				</layout:block>
			</div>
		</div>
	</div>

	<%--  <%@ include file="footer.jsp" %> --%>
</body>
</html>