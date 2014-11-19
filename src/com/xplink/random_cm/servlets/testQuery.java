package com.xplink.random_cm.servlets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.xplink.random_cm.datamodel.MemberBean;

public class testQuery {
	
	public static Connection connectDB(){
		Connection connection = null; 
		try { 

			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/db_goobre_cmr?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
			String username = "root"; 
			String password = "admin"; 
			connection = DriverManager.getConnection(url, username, password); 
			
			} catch (ClassNotFoundException e) { 
				// Could not find the database driver 
				e.printStackTrace();
				System.out.println("Could not find the database driver");
			} catch (SQLException e) { 
				// Could not connect to the database 
				System.out.println("Could not connect to the database");
			}
			return connection;
	}
	public static void main(String[] args) throws SQLException{
		String email = "im_daywin@yahoo.co.th";
		/*Boolean result = isExistMember(email);
		System.out.println("Exist user "+result);*/
		Connection con = connectDB();
		String sql = "SELECT email FROM cmr_member where email = 'im_daywin@yahoo.co.th'";
		//String sql = "UPDATE form cmr_member set status = 1 where email= "+email;
		try{		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//System.out.println("row"+row);
		MemberBean mb = new MemberBean();
		while (rs.next()) {
			//mb.setMemberid((rs.getInt("MEMBER_ID")));				
			mb.setEmail(rs.getString("EMAIL"));
			//mb.setStatus(rs.getString("STATUS"));
			//mb.setYear(rs.getString("YEAR"));
		}
		if(mb.getEmail().equals("im_daywin@yahoo.co.th")){
			System.out.println("Exist user");
		}
		else{
			System.out.println("No user");
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			con.close();
		}
		
		// test update status
//		MemberBean mb = new MemberBean();
//		mb.setMemberid(293);
//		mb.setName("Gookgook Gobgob");
//		mb.setEmail("gookgookgobgob@gmail.com");
//		mb.setSurname("Gobgob");
//		mb.setStatus("1");
//		try {
//			updateMember(mb);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	@SuppressWarnings("finally")
	public static Boolean isExistMember(String email) throws SQLException {
		ResultSet member = null;
		Connection con = connectDB();
		Statement stmtExistMember = con.createStatement();
		boolean existMember = false;
		
		String sql = "select * from cmr_member where email = '"+email+"'";
		try{
		member = stmtExistMember.executeQuery(sql);
		System.out.print("CCCC"+member.getString(3));
		} catch (Exception ex) {
			//logger.error("error:"+ex.toString(),ex);
		}finally{
			if(member != null){
				existMember = true;
				try{member.close();}catch(Exception ex){}}
			if(member != null)
				try{stmtExistMember.close();}catch(Exception ex){}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					//logger.error("exception:"+e.toString(),e);
				}
			}
			return existMember;
		}
	}
	
	public static boolean updateMember(MemberBean memberBean) throws Exception{
		 //logger.debug("[IN :updateMember]");
		 Connection con = connectDB();
		 Statement stmtUpdateMember= con.createStatement();
				 
		 boolean result = false;
			 
		 try {	 
			 	
				String sqlUpdateMember = "UPDATE cmr_member SET  NAME='"+memberBean.getName()+"'"+
										", SURNAME='"+memberBean.getSurname()+"'"+
										", EMAIL='"+memberBean.getEmail()+"'"+
										",USERNAME='"+memberBean.getUsername()+"'"+
										", 	STATUS='"+ memberBean.getStatus() +"'  WHERE MEMBER_ID='"+memberBean.getMemberid()+"'";
								
				int row_event = stmtUpdateMember.executeUpdate(sqlUpdateMember);				
				
				if (row_event > 0) {
					 //logger.debug("[--update Complete--]");
							
					result = true;
				}
				
				//con.commit();
				
		 }catch (Exception ex) {
			 		//con.rollback();
					//logger.debug("[exception DB:]"+ex.getMessage(),ex);
					throw ex;
		 }finally{
					if(stmtUpdateMember!= null){
						try{stmtUpdateMember.close();}catch(Exception ex){
							ex.getStackTrace();
						}
					}
					
					if (con != null) {
						try {
							con.close();
						} catch (Exception e) {
							//logger.error("Exception:" + e.toString(), e);
						}
					}
				}
				 //logger.debug("[OUT :updateMember]");
				return result;					
		}

 
}
