package holiday;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	//データソース（接続情報）を保存する変数ds(何度でも使用可）
		static DataSource ds;
		
		//データベースへの接続（Connectionオブジェクト）を取得する
		public Connection getConnection() throws Exception {
		    //データソースの初期化。dsが未初期化の場合に限り、最初の1回だけデータソースを取得
			if(ds==null){
				InitialContext ic=new InitialContext();
				ds=(DataSource)ic.lookup("java:/comp/env/jdbc/holiday");
			}
				//接続を取得し呼び出し元に返す
				return ds.getConnection();
				
				
			}
		//テーブル作成メソッド
		public void initializeDatabase() {
	        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
	            // PAIDHOLIDAY テーブル作成
	            String createPaidHolidayTable = """
	                CREATE TABLE IF NOT EXISTS PAIDHOLIDAY (
	                    ID INT PRIMARY KEY,
	                    GRANTHOLIDAY INT NOT NULL,
	                    DUTY INT NOT NULL,
	                    GET INT DEFAULT 0
	                )
	            """;
	            stmt.execute(createPaidHolidayTable);

	            
	         // DATEOFPAID テーブル作成
	            String createDateOfPaidTable = """
	                CREATE TABLE IF NOT EXISTS DATEOFPAID (
	                    ID SERIAL PRIMARY KEY,
	                    PAIDVACATIONDATE DATE NOT NULL
	                )
	            """;
	            stmt.execute(createDateOfPaidTable);
	            
	         // 初期データ挿入
	            String insertInitialData = """
	                INSERT INTO PAIDHOLIDAY (ID, GRANTHOLIDAY, DUTY, GET)
	                SELECT 1, 0, 0, 0
	                WHERE NOT EXISTS (SELECT 1 FROM PAIDHOLIDAY WHERE ID = 1)
	            """;
	            stmt.executeUpdate(insertInitialData);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	            
}
