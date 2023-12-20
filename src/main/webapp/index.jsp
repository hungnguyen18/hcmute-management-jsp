<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

	<%
	String error = (String) session.getAttribute("error");
	if (error != null && !error.isEmpty()) {
	%>
	<script type="text/javascript">
                alert("<%=error%>");
	</script>
	<%
	session.removeAttribute("error");
	%>
	<%
	}
	%>

	<div class="container mx-auto max-w-md mt-20">
		<h2 class="text-2xl font-bold text-center mb-6">Login</h2>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<div class="mb-4">
				<label for="username" class="block font-semibold mb-1">Username</label>
				<input type="text" id="username" name="username"
					placeholder="Enter your username"
					class="w-full px-4 py-2 border border-gray-300 rounded-lg">
			</div>
			<div class="mb-4">
				<label for="password" class="block font-semibold mb-1">Password</label>
				<input type="password" id="password" name="password"
					placeholder="Enter your password"
					class="w-full px-4 py-2 border border-gray-300 rounded-lg">
			</div>
			<div class="mb-4">
				<button type="submit"
					class="w-full bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600 rounded-">Login</button>
			</div>
		</form>
	</div>
</body>
</html> 




