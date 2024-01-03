
<header class="bg-gray-100 p-4">
	<div class="container mx-auto flex justify-end items-center">
		<div class="flex items-center space-x-4">
			<div class="relative">
				<button id="notificationButton" class="focus:outline-none">
					<svg class="h-6 w-6 text-black" fill="none" stroke="currentColor"
						viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
              <path stroke-linecap="round" stroke-linejoin="round"
							stroke-width="2"
							d="M12 19l9 2-9-18-9 18 9-2zm0 0v6m0 0s4 0 4.5 4H7c.5-4 4.5-4 4.5-4z"></path>
            </svg>
					<span id="notificationBadge"
						class="absolute top-0 right-0 bg-red-500 text-white rounded-full p-1 text-xs cursor-pointer">3</span>
				</button>
				<div id="notificationMenu"
					class="hidden absolute right-0 mt-2 bg-white p-2 rounded shadow origin-top-left">
					<!-- Your notification items go here -->
					<a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Notification
						1</a> <a href="#"
						class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Notification
						2</a> <a href="#"
						class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Notification
						3</a>
				</div>
			</div>
			<div class="relative">
				<button id="avatarButton" class="focus:outline-none">
					<img
						src="${pageContext.request.contextPath}/assets/3e61d2881a44b48e4cee2de6dd2a4c17.jpeg"
						alt="Avatar" class="w-8 h-8 rounded-full">
				</button>
				<div id="avatarMenu"
					class="hidden absolute right-0 mt-2 bg-white p-2 rounded shadow origin-top-left">
					<!-- Your menu items go here -->
					<a href="profile.jsp"
						class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Profile</a>
					<a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Settings</a>
					<a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-200">Logout</a>
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


