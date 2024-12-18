package holiday;

//取得状況管理
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/view-status")
public class ViewStatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // データベースからデータ取得
        PaidHolidayDAO dao = new PaidHolidayDAO();
        // 戻り値を変数に格納
        PaidHoliday paidHoliday = dao.getPaidHoliday();
        
        //paidHolidayがnullの場合の対処（エラーメッセージ表示）
        if(paidHoliday == null) {
        	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve paid holiday data.");
            return;
        }
        
        //データの使用（必要なプロパティをpaidHolidayオブジェクトから取得して、
        //計算とリクエスト属性の設定
        int remaining = paidHoliday.getGrant() - paidHoliday.getGet();
        request.setAttribute("grantholiday", paidHoliday.getGrant());
        request.setAttribute("get", paidHoliday.getGet());
        request.setAttribute("remaining", remaining);
        request.setAttribute("duty", paidHoliday.getDuty());
        
        // JSPに転送
        RequestDispatcher dispatcher = request.getRequestDispatcher("view-status.jsp");
        dispatcher.forward(request, response);
    }
    
    
}
