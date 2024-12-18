package holiday;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reset-data")
public class ResetDataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // DAO を使用してデータリセット
        PaidHolidayDAO dao = new PaidHolidayDAO();
        dao.resetData();

        // リセット後にホーム画面へリダイレクト
        response.sendRedirect("home");
    }
}
