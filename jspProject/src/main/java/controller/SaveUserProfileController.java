package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserProfileDAO;

@WebServlet("/SaveUserProfileController")
public class SaveUserProfileController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		int userId = (Integer) session.getAttribute("userId");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthDate = request.getParameter("birthDate");
		String calendarType = request.getParameter("calendarType");
		String birthTime = request.getParameter("birthTime");

		try {
			UserProfileDAO dao = new UserProfileDAO();
			dao.saveOrUpdateProfile(userId, name, gender, birthDate, calendarType, birthTime);

			session.setAttribute("name", name);
			session.setAttribute("gender", gender);
			session.setAttribute("birthDate", birthDate);
			session.setAttribute("calendarType", calendarType);
			session.setAttribute("birthTime", birthTime);

			response.sendRedirect("Input.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "프로필 저장 중 오류 발생");
		}
	}
}
