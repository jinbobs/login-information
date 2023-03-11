import java.sql.*;

public class Database {
	Connection con = null;
	Statement stmt = null;
	
	 
	
	Database() {	//데이터베이스에 연결한다.
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(
	            		"jdbc:mysql://localhost:3306/login", "root", "mite");
	            System.out.println("데이터베이스에 접속했습니다.");
	            //con.close();
	        }
	        catch (ClassNotFoundException cnfe) {
	            System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
	        }
	        catch (SQLException se) {
	            System.out.println("SQL에러입니다. " + se.getMessage());
	        }
	    }

	

	// 로그인 정보를 확인 
	boolean logincheck(String _i, String _p) {
		boolean flag = false;
		
		String id = _i;
		String pw = _p;
		
		try {
			stmt = con.createStatement();
			String checkingStr = "SELECT password FROM register WHERE id ='" + id + "'";
			ResultSet result = stmt.executeQuery(checkingStr);
			
			
			int count = 0;
			while(result.next()) {
				if(pw.equals(result.getString("password"))) {
					flag = true;
					System.out.println("로그인 성공");
				}
				
				else {
					flag = false;
					System.out.println("로그인 실패");
				}
				count++;
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("로그인 실패 > " + e.toString());
		}
		
		return flag;
	}
	
	boolean joinCheck(String _i, String _p) {
		boolean flag = false;
		
		String id = _i;
		String pw = _p;
			
		try {
			String insertStr = "INSERT INTO register VALUES('" + id + "', '" + pw + "')";
			stmt.executeUpdate(insertStr);
				
			flag = true;
			System.out.println("회원가입 성공");
		} catch(Exception e) {
			flag = false;
			System.out.println("회원가입 실패 > " + e.toString());
		}
			
		return flag;
	}
	
}