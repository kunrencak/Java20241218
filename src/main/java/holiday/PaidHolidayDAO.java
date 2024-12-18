package holiday;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaidHolidayDAO extends DAO {
	
	public PaidHolidayDAO() {
        // テーブル作成を初期化時に実行
        initializeDatabase();
    }
	
	//年間付与日数、取得義務日数をDBに登録
	public void updateGrantAndDuty(int grant, int duty) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE PAIDHOLIDAY SET GRANTHOLIDAY = ?, DUTY = ? WHERE ID = 1")) {
            stmt.setInt(1, grant);
            stmt.setInt(2, duty);
            stmt.executeUpdate();
        } catch (SQLException e) {
        	//SQlのエラーを表示
            e.printStackTrace();
        }catch (Exception e) {
        	//getConnection()から発生するエラーを処理
        	e.printStackTrace();
        }
	}
        
        //有休取得日数一覧取得
        public PaidHoliday getPaidHoliday() {
        	PaidHoliday holiday = null;
        	try (Connection conn = getConnection();
        		 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PAIDHOLIDAY WHERE ID = 1");
	             ResultSet rs = stmt.executeQuery()) {
        		if (rs.next()) {
        			holiday = new PaidHoliday(rs.getInt("grantholiday"), rs.getInt("duty"), rs.getInt("get"));
        		   }
        	} catch (SQLException e) {
            	//SQlのエラーを表示
        		e.printStackTrace();
        	}catch (Exception e) {
            	//getConnection()から発生するエラーを処理
            	e.printStackTrace();
        	}
        	return holiday;
        	}
        
        
        //「休む」ボタンをクリックしたときの操作
        public void addTakenLeave(String paidDate) {
        	try (Connection conn = getConnection()){
        		// 更新：　取得日数を+１
        		PreparedStatement stmt1 = conn.prepareStatement("UPDATE PAIDHOLIDAY SET GET = GET + 1 WHERE ID = 1");
                stmt1.executeUpdate();
                
                // 挿入：　日付を保存
                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO DATEOFPAID (PAIDVACATIONDATE) VALUES (?)");
                stmt2.setDate(1, Date.valueOf(paidDate));
                stmt2.executeUpdate();
        	} catch (SQLException e) {
            	//SQlのエラーを表示
        		e.printStackTrace();
        	}catch (Exception e) {
            	//getConnection()から発生するエラーを処理
            	e.printStackTrace();
        	}
        	
        }
        
        public void resetData() {
            try (Connection conn = getConnection()) {
                // PAIDHOLIDAY テーブルをリセット
                PreparedStatement stmt1 = conn.prepareStatement("UPDATE PAIDHOLIDAY SET GRANTHOLIDAY = 0, DUTY = 0, GET = 0 WHERE ID = 1");
                stmt1.executeUpdate();

                // DATEOFPAID テーブルの全データを削除
                PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM DATEOFPAID");
                stmt2.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


