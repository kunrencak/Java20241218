package holiday;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/take-leave")
public class TakeLeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paidDate = request.getParameter("paidDate");

        // 取得日数を増加しデータベースに登録
        PaidHolidayDAO dao = new PaidHolidayDAO(); 
        dao.addTakenLeave(paidDate);

        response.sendRedirect("view-status");
    }
}
