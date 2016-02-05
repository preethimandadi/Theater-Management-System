package otms;

import java.io.*;
import java.sql.*;

public class Theater {
	private static Connection getConnection() throws Exception {
		String Driver = "oracle.jdbc.driver.OracleDriver";
		String DBLink = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
		String UserName = "pmandad1";
		String Password = "cs425";
		Class.forName(Driver);
		return DriverManager.getConnection(DBLink, UserName, Password);
	}
	
//	For Question 4
	public static void setSchedule() throws Exception {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		String query4 = null;
		Connection con = null;
		Statement st4 = null;
		ResultSet rs4 = null;
		ResultSetMetaData rsmd4 = null;
		int colNumber;
		try{
			con = getConnection();
			
			st4 = con.createStatement();
			
			// print staff who are not allocated			
//			query4 = "Select staff_id, staff_first_name, staff_last_name from t_staff_details";
			query4 = "Select * from t_staff_details";
			rs4 = st4.executeQuery(query4);
			System.out.println(rs4);
			rsmd4 = rs4.getMetaData();
			colNumber = rsmd4.getColumnCount();
			
			while(rs4.next()){
				for (int i = 1; i<= colNumber; i++){
					if ( i >= 1){
						System.out.print("|");
						String columnValue = rs4.getString(i);
						System.out.print(columnValue);
					}
				}
				System.out.println("");
			}
			rs4.close();
			
			System.out.println("Select Staff ID to set the schedule");			
			int staffID = Integer.parseInt(br.readLine());
			
// 			Printing theaters list
			query4 = "Select * from s_theater";
			rs4 = st4.executeQuery(query4);
			
			rsmd4 = rs4.getMetaData();
			colNumber = rsmd4.getColumnCount();
			while(rs4.next()){
				for (int i = 1; i<= colNumber; i++){
					if ( i >= 1){
						System.out.print("|");
						String columnValue = rs4.getString(i);
						System.out.print(columnValue);
					}
				}
				System.out.println("");
			}
			rs4.close();
			
			System.out.println("Select TheaterID");			
			int theaterID = Integer.parseInt(br.readLine());
			
			System.out.println("Enter From time: hhmm");			
			int fromTime = Integer.parseInt(br.readLine());
			
			System.out.println("Enter To time: hhmm");	
			int toTime = Integer.parseInt(br.readLine());
			
//			pradeep should write query		
//			query4 = "Select * from t_staff_allocation where theater_id = " + theaterID;
			query4 = "select SA.STAFF_ID " +
			 "from T_STAFF_ALLOCATION               SA " +             
			 "join T_STAFF_DETAILS                  SD " +
			   "on SA.STAFF_ID = SD.STAFF_ID " +
			 "join S_STAFF_FUNCTION                 SF " +
			   "on SD.FUNCTION_ID = SF.FUNCTION_ID " +
			"where SD.STAFF_ID = " + staffID + 
			  "and SA.THEATER_ID = "+ theaterID +
			  "and ( SA.FROM_TIME Between to_date(" + fromTime + ",'HH24MI') AND " +
										" to_date(" + toTime + ",'HH24MI') OR " + 
			  "SA.to_TIME Between to_date(" + fromTime + ",'HH24MI') AND " +
										" to_date(" + toTime + ",'HH24MI'))";
			rs4 = st4.executeQuery(query4);
			
			if(rs4 != null){
//				while(rs4.next())
//				{
//					t_staffID = rs4.getInt(1);
//				}
				System.out.println("Staff is already allocated ");
				System.exit(0);
			}else{
				query4 = "Insert into t_staff_allocation " +
						"Values(" + staffID + "," + theaterID + "," +
						"to_date(" + fromTime +",'HH24,MI')," +
						"to_date(" + toTime +",'HH24,MI')";
				
				int success = st4.executeUpdate(query4);
				if(success > 1){
					System.out.println("Successfully Added..");
				}
			}
		}catch(SQLException SQLE){
			SQLE.printStackTrace();
		}catch(Exception E){
			E.printStackTrace();
		}finally{
			if(rs4 != null){
				rs4.close();
			}
			if(st4 != null){
				st4.close();
			}
			if(con != null){
				con.close();
			}
		}
	}
	
	public static void updateSchedule() throws Exception {
BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		String query4 = null;
		Connection con = null;
		Statement st4 = null;
		ResultSet rs4 = null;
		ResultSetMetaData rsmd4 = null;
		int colNumber;
		try{
			con = getConnection();
			
			st4 = con.createStatement();
			
			// print staff who are not allocated			
//			query4 = "Select staff_id, staff_first_name, staff_last_name from t_staff_details";
			query4 = "Select * from t_staff_details";
			rs4 = st4.executeQuery(query4);
			System.out.println(rs4);
			rsmd4 = rs4.getMetaData();
			colNumber = rsmd4.getColumnCount();
			
			while(rs4.next()){
				for (int i = 1; i<= colNumber; i++){
					if ( i >= 1){
						System.out.print("|");
						String columnValue = rs4.getString(i);
						System.out.print(columnValue);
					}
				}
				System.out.println("");
			}
			rs4.close();
			
			System.out.println("Select Staff ID to Update the schedule");			
			int staffID = Integer.parseInt(br.readLine());
			
// 			Printing theaters list
			query4 = "Select * from s_theater";
			rs4 = st4.executeQuery(query4);
			
			rsmd4 = rs4.getMetaData();
			colNumber = rsmd4.getColumnCount();
			while(rs4.next()){
				for (int i = 1; i<= colNumber; i++){
					if ( i >= 1){
						System.out.print("|");
						String columnValue = rs4.getString(i);
						System.out.print(columnValue);
					}
				}
				System.out.println("");
			}
			rs4.close();
			
			System.out.println("Select TheaterID");			
			int theaterID = Integer.parseInt(br.readLine());
			
			System.out.println("Enter From time hh:mm");			
			int fromTime = Integer.parseInt(br.readLine());
			
			System.out.println("Enter To time hh:mm");	
			int toTime = Integer.parseInt(br.readLine());
			
//			pradeep should write query		
			query4 = "select SA.STAFF_ID " +
					 "from T_STAFF_ALLOCATION               SA " +             
					 "join T_STAFF_DETAILS                  SD " +
					   "on SA.STAFF_ID = SD.STAFF_ID " +
					 "join S_STAFF_FUNCTION                 SF " +
					   "on SD.FUNCTION_ID = SF.FUNCTION_ID " +
					"where SD.STAFF_ID = " + staffID + 
					  "and SA.THEATER_ID = "+ theaterID +
					  "and ( SA.FROM_TIME Between to_date(" + fromTime + ",'HH24MI') AND " +
												" to_date(" + toTime + ",'HH24MI') OR " + 
					  "SA.to_TIME Between to_date(" + fromTime + ",'HH24MI') AND " +
												" to_date(" + toTime + ",'HH24MI'))";
			rs4 = st4.executeQuery(query4);
			
			if(rs4.next()){
				System.out.println("Staff is already allocated ");
				System.exit(0);
			}else{
				query4 = "update t_staff_allocation SET theaterID = " + theaterID +
						", fromTime = " + "to_date(" + fromTime +",'HH24,MI')," +
						", toTime = " + "to_date(" + toTime +",'HH24,MI') Where" +
						"staff_id =" + staffID;
				st4.executeUpdate(query4);
				System.out.println("Successfully Added..");
			}
		}catch(SQLException SQLE){
			SQLE.printStackTrace();
		}catch(Exception E){
			E.printStackTrace();
		}finally{
			if(rs4 != null){
				rs4.close();
			}
			if(st4 != null){
				st4.close();
			}
			if(con != null){
				con.close();
			}
		}
	}
	
	public static void question4() throws Exception{
		BufferedReader br4=new BufferedReader (new InputStreamReader(System.in));
		System.out.println("Select one option");
		System.out.println("1. Set Schedule");
		System.out.println("2. Update Schedule");
		System.out.println("3. Exit");
		int question4Input = Integer.parseInt(br4.readLine());
		
		switch (question4Input){
			case 1:	
				setSchedule();
				break;
			case 2:	
				updateSchedule();
				break;
			case 3:	
				break;
		}
		
	}
	
	
//	For Question 5
	public static void question5() throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		String query5 = null;
		
		Connection con = null;
		Statement st5 = null;
		ResultSet rs5 = null;
		ResultSet rs51 = null;
		ResultSetMetaData rsmd5 = null;
		
		int silverPoints = 0, goldPoints = 0, membershipID = 0;
		try{
			con = getConnection();
			
			st5 = con.createStatement();
//			query to get points for gold membership
			query5 = "select membership_id from t_membership where membership_type = 'gold'";
			rs5 = st5.executeQuery(query5);
			while(rs5.next()){
				membershipID = rs5.getInt(1); 
			}
			rs5.close();
			rs5 = null;
			query5 = "select points from t_offer where membership_id = " + membershipID;
			rs51 = st5.executeQuery(query5);
			while(rs51.next()){
				goldPoints = rs51.getInt(1);
			}
			
			System.out.println("Enter number of points for silver");
			silverPoints = Integer.parseInt(br.readLine());
			
			if (silverPoints > goldPoints){
				System.out.println("Silver Points cannot be greater than gold points");
			}else{
				query5 = "select membership_id from t_membership where membership_type = 'silver'";
				rs5 = st5.executeQuery(query5);
				while(rs5.next()){
					membershipID = rs5.getInt(1); 
				}
				query5 = "update t_offer SET points = " + silverPoints + 
						"where membership_id = " + membershipID;
				st5.executeUpdate(query5);
				System.out.println("Successfully Updated..");
			}
		}finally{
			if(rs5 != null){
				rs5.close();
			}
			if(st5 != null){
				st5.close();
			}
			if(con != null){
				con.close();
			}
		}
	}

//	For Question 6
	public static void registerUser() throws Exception{
//		select max id from and increment to get id
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		String query6 = null;
		
		Connection con = null;
		Statement st6 = null;
		ResultSet rs6 = null;
		ResultSetMetaData rsmd6 = null;
		int genUserID = 0, colNumber = 0;
		try{
			con = getConnection();
			
			st6 = con.createStatement();
			query6 = "select MAX(user_id) from t_userdetails";
			rs6 = st6.executeQuery(query6);
			
			rsmd6 = rs6.getMetaData();
			colNumber = rsmd6.getColumnCount();
			while(rs6.next()){
				genUserID = rs6.getInt(1);
			}
			genUserID = genUserID + 1;
			rs6.close();
			
//			ask rest of the inputs from user			
			System.out.println("Enter First Name");
			String firstName = br.readLine();
			
			System.out.println("Enter Last Name");
			String lastName = br.readLine();
			
			System.out.println("Enter Address");
			String address = br.readLine();
			
			System.out.println("Enter ZipCode");
			int ZipCode = Integer.parseInt(br.readLine());
			
			query6 = "Select * from t_membership";
			rs6 = st6.executeQuery(query6);
			
			rsmd6 = rs6.getMetaData();
			colNumber = rsmd6.getColumnCount();
			while(rs6.next()){
				for (int i = 1; i<= colNumber; i++){
					if ( i >= 1){
						System.out.print("|");
						String columnValue = rs6.getString(i);
						System.out.print(columnValue);
					}
				}
				System.out.println("");
			}
			
			System.out.println("Selet Membership ID");
			int MembershipID = Integer.parseInt(br.readLine());
			
			System.out.println("Enter Phone Number");
			String phoneNumber = br.readLine();
			
			System.out.println("Enter card type");
			String cardType = br.readLine();
			
			
			System.out.println("Enter Card Number");
			String cardNumber = br.readLine();
			
			System.out.println("Enter expiry");
			String expiry = br.readLine();
			
			query6 = "Insert INTO t_userdetails Values ('" + genUserID + "','" +
								firstName + "','" + lastName + "','" + address +  "','"
								+ ZipCode + "','" + MembershipID + "','" + phoneNumber + "','"
								+ cardType + "','" 
								+ cardNumber + "',to_date('" + expiry + "','mm/yy'))";

			st6.executeUpdate(query6);
			System.out.println("Your UserID is: " + genUserID);
			System.out.println("Successfully Added..");
		}catch(SQLException SQLE){
			SQLE.printStackTrace();
		}catch(Exception E){
			E .printStackTrace();
		}finally{
			if(rs6 != null){
				rs6.close();
			}
			if(st6 != null){
				st6.close();
			}
			if(con != null){
				con.close();
			}
		}
	}
	
	public static void bookTicket(int guest) throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
//		prompt user to enter user id
		System.out.println("Enter UserID:");
		int userID = Integer.parseInt(br.readLine());
		
//		display shows from shows table and prompt user to select show
		String query6 = null;
		
		Connection con = null;
		Statement st6 = null;
		ResultSet rs6 = null;
		ResultSetMetaData rsmd6 = null;
		int showIDInput = 0, colNumber = 0;
		try{
			con = getConnection();
			
			st6 = con.createStatement();
//			checking user id 
			query6 = "select * from t_userdetails where user_id = " + userID;
			rs6 = st6.executeQuery(query6);
			if(rs6 != null){
				rs6.close();
				query6 = "select sh.show_id, mo.movie_name, th.ADDRESS, th.zipcode, sh.show_date_time"  
						+ " from t_shows  sh " 
						+ "join t_movies  mo " 
						+ "on sh.movie_id = mo.movie_id " 
						+ "join s_theater  th " 
						+ "on th.theater_id = sh.theater_id " 
						+ "where sh.theater_id = th.theater_id   ";
				rs6 = st6.executeQuery(query6);
				
				rsmd6 = rs6.getMetaData();
				colNumber = rsmd6.getColumnCount();
				while(rs6.next()){
					int showID = rs6.getInt(1);
					String movieName = rs6.getString(2);
					String address = rs6.getString(3);
					int zipcode = rs6.getInt(4);
					Date date = rs6.getDate(5);
					System.out.print(showID + "|" + movieName + "|" + address + "|" +
									 zipcode + "|" + date);
					System.out.println(""); 
				}
				
				System.out.println("Enter ShowID");
				showIDInput = Integer.parseInt(br.readLine());
				
			}else{
				System.out.println("UserID does not exists");
			}
		}catch(SQLException SQLE){
			SQLE.printStackTrace();
		}catch(Exception E){
			E .printStackTrace();
		}finally{
			if(rs6 != null){
				rs6.close();
			}
			if(st6 != null){
				st6.close();
			}
			if(con != null){
				con.close();
			}
		}
		if(guest == 1){
			System.out.println("Enter Card Number");
			String cardNumber = br.readLine();
			
			System.out.println("Enter Name on Card");
			String cardName = br.readLine();
			
			System.out.println("Enter expiry");
			String expiry = br.readLine();
		}
		
//		asking user to give credit card balance
		System.out.println("Have enough balance");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int balanceInput = Integer.parseInt(br.readLine());
		
		if(balanceInput == 1){
			System.out.println("Booking Successfully6 done..");
		}else{
			System.out.println("you cannot book tickets.. Not enough balance in credit card");
		}
	}
	
	public static void question6() throws Exception{
		int guest;
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		System.out.println("Are you a registered user:");
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.println("3. Exit");
		int registerInput = Integer.parseInt(br.readLine());
		
		switch(registerInput){
		case 1:
			guest = 1;
			bookTicket(guest);
			break;
		case 2:
			System.out.println("Do you want to register:");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println("3. Exit");
			int registerResponseInput = Integer.parseInt(br.readLine());
			
			switch(registerResponseInput){
			case 1:
				registerUser();
				guest = 0;
				bookTicket(guest);
				break;
			case 2:
				guest = 1;
				bookTicket(guest);
				break;
				
			case 3:
				break;
			default:
				break;
			}
			
			break;
		case 3:
			break;
		default:
			break;
		}
	}
	
//	Initial Inputs
	public static void selectOption() throws Exception{
		System.out.println("Select one of the option:");
		System.out.println("4. Question 4");
		System.out.println("5. Question 5");
		System.out.println("6. Question 6");
		System.out.println("Press any other Number to exit");
		
		BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
		int questionInput = Integer.parseInt(br.readLine());
		switch (questionInput){
		case 4:
			question4();
			selectOption();
			break;
		case 5:
			question5();
			selectOption();
			break;
		case 6:
			question6();
			selectOption();
			break;
		default:
			System.exit(0);
			break;
		}
	}
	
	public static void main (String args[]) throws Exception{
		System.out.println("--- Welcome to Theater Management System ---");
		selectOption();
		System.exit(0);
	}
}

