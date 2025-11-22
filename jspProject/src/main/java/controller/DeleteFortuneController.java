package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FortuneDAO;

@WebServlet("/DeleteFortuneController")
public class DeleteFortuneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			// URL에서 recordId 파라미터로 운세 기록의 ID를 가져옴
			int recordId = Integer.parseInt(request.getParameter("recordId"));

			// FortuneDAO를 사용하여 해당 운세 기록 삭제
			FortuneDAO dao = new FortuneDAO();
			dao.deleteRecordById(recordId);

			// 운세 기록 삭제 후 관리자 페이지로 리다이렉트
			response.sendRedirect("AdminFortunePage.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "운세 기록 삭제 중 오류 발생");
		}
	}
}
