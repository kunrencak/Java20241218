package holiday;

//有給休暇設定
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register-leave")
public class RegisterLeaveServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("register-leave.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String grantStr = request.getParameter("grantholiday");
        String dutyStr = request.getParameter("duty");

        // 入力チェック（空チェック）
        if (grantStr == null || grantStr.isEmpty() || dutyStr == null || dutyStr.isEmpty()) {
            request.setAttribute("error", "付与日数と取得義務日数を入力してください。");
            request.getRequestDispatcher("register-leave.jsp").forward(request, response);
            return; // 処理終了
        }

        try {
            // 数値に変換
            int grant = Integer.parseInt(grantStr);
            int duty = Integer.parseInt(dutyStr);

            // データベースに保存
            PaidHolidayDAO dao = new PaidHolidayDAO();
            dao.updateGrantAndDuty(grant, duty);

            // 登録完了後、トップ画面にリダイレクト
            response.sendRedirect("home");
        } catch (NumberFormatException e) {
            // 数値以外が入力された場合のエラー処理
            request.setAttribute("error", "付与日数と取得義務日数は数値で入力してください。");
            request.getRequestDispatcher("register-leave.jsp").forward(request, response);
        }
    }
}