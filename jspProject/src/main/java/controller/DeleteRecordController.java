package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FortuneDAO;

@WebServlet("/DeleteRecordController")
public class DeleteRecordController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			// 삭제할 기록의 ID 가져오기
			int recordId = Integer.parseInt(request.getParameter("id"));

			// DAO를 사용하여 기록 삭제
			FortuneDAO dao = new FortuneDAO();
			dao.deleteRecordById(recordId);

			// 삭제 후 기록 보기 페이지로 이동
			response.sendRedirect("Records.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "기록 삭제 중 오류 발생");
		}
	}
}
