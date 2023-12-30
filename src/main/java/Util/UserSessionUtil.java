package Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Bean.UserBean;

public class UserSessionUtil {

	private static final String USER_ATTRIBUTE = "user";

	public static UserBean getUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		return (UserBean) session.getAttribute(USER_ATTRIBUTE);
	}

	public static void addUser(HttpServletRequest request, UserBean user) {
		HttpSession session = request.getSession(true);
		session.setAttribute(USER_ATTRIBUTE, user);
	}

	public static void removeUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.removeAttribute(USER_ATTRIBUTE);
		}
	}

	public static boolean isUserLoggedIn(HttpServletRequest request, String username) {
		UserBean loggedInUser = getUser(request);
		return loggedInUser != null && loggedInUser.getUsername().equals(username);
	}
}
