package otms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Final {
	private static Connection getConnection() throws Exception {
		String Driver = "oracle.jdbc.driver.OracleDriver";
		String DBLink = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
		String UserName = "pgollave";
		String Password = "YCsBUo3...uK3Y15";
		Class.forName(Driver);
		return DriverManager.getConnection(DBLink, UserName, Password);
	}

	public static int login() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userID = null;
		String password = null;

		System.out.println("Enter UserID");
		userID = br.readLine();

		System.out.println("Enter Password");
		password = br.readLine();

		String query = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			con = getConnection();
			st = con.createStatement();
			// String tableName = selectTable();
			query = "SELECT user_id FROM t_logindetails Where user_id = '" + userID + "' AND password = '" + password
					+ "'";

			rs = st.executeQuery(query);
			rsmd = rs.getMetaData();

			while (rs.next()) {
				String columnValue = rs.getString(1);

				return Integer.parseInt(userID);
			}
		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return 0;
	}

	public static String selectTable() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("s_credit_source");
		System.out.println("s_genre");
		System.out.println("s_movie_status");
		System.out.println("s_offer_type");
		System.out.println("s_screens");
		System.out.println("s_staff_allocation");
		System.out.println("s_theater");
		System.out.println("t_booking_history");
		System.out.println("t_credits_earned");
		System.out.println("t_forum");
		System.out.println("t_forum_replies");
		System.out.println("t_logindetails");
		System.out.println("t_membership");
		System.out.println("t_movie_genre");
		System.out.println("t_movies");
		System.out.println("t_offer");
		System.out.println("t_shows");
		System.out.println("t_staff_allocation");
		System.out.println("t_staff_details");
		System.out.println("t_transaction_history");
		System.out.println("t_userdetails");
		System.out.println("");
		System.out.println("Enter the table name:");

		String tableName = br.readLine();
		return tableName;
	}

	public static void select() throws Exception {
		String query = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int colNumber = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			String tableName = selectTable();
			query = "SELECT * FROM " + tableName;

			rs = st.executeQuery(query);
			rsmd = rs.getMetaData();
			colNumber = rsmd.getColumnCount();

			for (int i = 1; i <= colNumber; i++) {
				if (i >= 1) {
					System.out.print("|");
					String columnValue = rsmd.getColumnName(i);
					System.out.print(columnValue + "\t\t");
				}
			}
			System.out.println("");

			while (rs.next()) {
				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rs.getString(i);
						System.out.print(columnValue + "\t\t\t");
					}
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("");
			rs.close();

		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static void insert() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String query = null;
		String insertValues = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int colNumber = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			String tableName = selectTable();
			query = "SELECT * FROM " + tableName;

			rs = st.executeQuery(query);
			rsmd = rs.getMetaData();
			colNumber = rsmd.getColumnCount();
			insertValues = "Insert Into " + tableName + " values (";

			for (int i = 1; i <= colNumber; i++) {
				if (i >= 1 && i < colNumber) {
					String columnValue = rsmd.getColumnName(i);
					System.out.println("Enter the value for " + columnValue + "\t\t");
					insertValues = insertValues + "'" + br.readLine() + "', ";
				}
				if (i == colNumber) {
					String columnValue = rsmd.getColumnName(i);
					System.out.println("Enter the value for " + columnValue + "\t\t");
					insertValues = insertValues + "'" + br.readLine() + "'";
				}
			}
			insertValues = insertValues + ")";
			System.out.println("");
			rs.close();
			st.close();

			st = con.createStatement();
			rs = st.executeQuery(insertValues);
			if (rs.next()) {
				System.out.println("Inserted Successfully ");
			} else {
				System.out.println("Insert not Successful ");
			}

		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static void update() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String query = null;
		String updateValues = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int colNumber = 0, stop = 0, choice = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			String tableName = selectTable();
			query = "SELECT * FROM " + tableName;

			rs = st.executeQuery(query);
			rsmd = rs.getMetaData();
			colNumber = rsmd.getColumnCount();
			updateValues = "Update " + tableName + " SET ";

			for (int i = 1; i <= colNumber; i++) {
				if (i >= 1) {
					String columnValue = rsmd.getColumnName(i);
					System.out.println(columnValue);
				}
			}

			while (stop == 0) {
				System.out.println("Enter the SET column Name");
				updateValues = updateValues + br.readLine();
				System.out.println("Enter the Column Value");
				updateValues = updateValues + " = '" + br.readLine() + "'";
				System.out.println("do you want to set values for more columns");
				System.out.println("Yes (0) / No (1)");
				stop = Integer.parseInt(br.readLine());
				if (stop == 0) {
					updateValues = updateValues + ", ";
				}
			}

			stop = 0;
			updateValues = updateValues + " WHERE ";

			while (stop == 0) {
				System.out.println("Enter the WHERE column Name");
				updateValues = updateValues + br.readLine();
				System.out.println("Enter the Column Value");
				updateValues = updateValues + " = '" + br.readLine() + "'";
				System.out.println("do you want to set more WHERE Conditions");
				System.out.println("Yes (0) / No (1)");
				stop = Integer.parseInt(br.readLine());
				if (stop == 0) {
					System.out.println("Select AND (1) or OR (2) Condition");
					choice = Integer.parseInt(br.readLine());
					if (choice == 1) {
						updateValues = updateValues + " AND ";
					}
					if (choice == 2) {
						updateValues = updateValues + " OR ";
					}
				}
			}

			System.out.println("");
			rs.close();
			st.close();

			st = con.createStatement();
			rs = st.executeQuery(updateValues);
			if (rs.next()) {
				System.out.println("Updated Successfully ");
			} else {
				System.out.println("Update not Successful ");
			}

		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static void delete() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String query = null;
		String deleteValues = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int colNumber = 0, stop = 0, choice = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			String tableName = selectTable();
			query = "SELECT * FROM " + tableName;

			rs = st.executeQuery(query);
			rsmd = rs.getMetaData();
			colNumber = rsmd.getColumnCount();
			deleteValues = "Delete FROM " + tableName + " WHERE ";

			for (int i = 1; i <= colNumber; i++) {
				if (i >= 1) {
					String columnValue = rsmd.getColumnName(i);
					System.out.println(columnValue);
				}
			}

			while (stop == 0) {
				System.out.println("Enter the WHERE column Name");
				deleteValues = deleteValues + br.readLine();
				System.out.println("Enter the Column Value");
				deleteValues = deleteValues + " = " + br.readLine();
				System.out.println("do you want to set more WHERE Conditions");
				System.out.println("Yes (0) / No (1)");
				stop = Integer.parseInt(br.readLine());
				if (stop == 0) {
					System.out.println("Select AND (1) or OR (2) Condition");
					choice = Integer.parseInt(br.readLine());
					if (choice == 1) {
						deleteValues = deleteValues + " AND ";
					}
					if (choice == 2) {
						deleteValues = deleteValues + " OR ";
					}
				}
			}

			System.out.println("");
			rs.close();
			st.close();

			st = con.createStatement();
			rs = st.executeQuery(deleteValues);
			if (rs.next()) {
				System.out.println("Deleted Successfully ");
			} else {
				System.out.println("Delete not Successful ");
			}

		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static void option1() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Select one option");
		System.out.println("1. Select");
		System.out.println("2. Insert");
		System.out.println("3. Update");
		System.out.println("4. Delete");
		System.out.println("5. Exit");
		int userSelection = Integer.parseInt(br.readLine());

		switch (userSelection) {
		case 1:
			select();
			break;
		case 2:
			insert();
			break;
		case 3:
			update();
			break;
		case 4:
			delete();
			break;
		case 5:
			break;
		}
	}

	public static int register() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String query = null;

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int genUserID = 0, colNumber = 0;
		try {
			con = getConnection();

			st = con.createStatement();
			query = "select MAX(user_id) from t_userdetails";
			rs = st.executeQuery(query);

			rsmd = rs.getMetaData();
			colNumber = rsmd.getColumnCount();
			while (rs.next()) {
				genUserID = rs.getInt(1);
			}
			genUserID = genUserID + 1;
			rs.close();

			System.out.println("Enter password");
			String password = br.readLine();

			// ask rest of the inputs from user
			System.out.println("Enter First Name");
			String firstName = br.readLine();

			System.out.println("Enter Last Name");
			String lastName = br.readLine();

			System.out.println("Enter Address");
			String address = br.readLine();

			System.out.println("Enter ZipCode");
			int ZipCode = Integer.parseInt(br.readLine());
			
			System.out.println("Enter E-Mail ID");
			String emailID = br.readLine();
			

			query = "Select * from t_membership";
			rs = st.executeQuery(query);

			rsmd = rs.getMetaData();
			colNumber = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rs.getString(i);
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

			query = "Insert INTO t_userdetails Values ('" + genUserID + "','" + firstName + "','" + lastName + "','"
					+ address + "','" + ZipCode + "','" + emailID + "','" + MembershipID + "','" + phoneNumber + "','" + cardType + "','"
					+ cardNumber + "',to_date('" + expiry + "','mm/yy'))";

			st.executeUpdate(query);
			System.out.println("Your UserID is: " + genUserID);
			System.out.println("Successfully Added..");

			rs.close();

			query = "insert into t_logindetails values ( '" + genUserID + "','" + password + "')";
			st.executeUpdate(query);

			return genUserID;
		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
			return 0;
		} catch (Exception E) {
			E.printStackTrace();
			return 0;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static void bookTicket(int userID) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// display shows from shows table and prompt user to select show
		String query = null;

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int showID = 0, colNumber = 0, numberOfSeats = 0;
		String emailID = null;
		try {
			con = getConnection();

			st = con.createStatement();
			// checking user id
			query = "select * from t_userdetails where user_id = " + userID;
			rs = st.executeQuery(query);
			if (rs != null) {
				rs.close();
				query = "select sh.show_id, mo.movie_name, th.ADDRESS, th.zipcode, sh.show_date_time"
						+ " from t_shows  sh " + "join t_movies  mo " + "on sh.movie_id = mo.movie_id "
						+ "join s_theater  th " + "on th.theater_id = sh.theater_id "
						+ "where sh.theater_id = th.theater_id   ";
				rs = st.executeQuery(query);

				rsmd = rs.getMetaData();
				colNumber = rsmd.getColumnCount();
				while (rs.next()) {
					showID = rs.getInt(1);
					String movieName = rs.getString(2);
					String address = rs.getString(3);
					int zipcode = rs.getInt(4);
					String date = rs.getString(5);
					System.out.print(showID + "|" + movieName + "|" + address + "|" + zipcode + "|" + date);
					System.out.println("");
				}
				showID = 0;
				System.out.println("Enter ShowID");
				showID = Integer.parseInt(br.readLine());

				System.out.println("Enter Number of Seats");
				numberOfSeats = Integer.parseInt(br.readLine());

			} else {
				System.out.println("UserID does not exists");
			}

			if (userID == 0 && numberOfSeats != 0) {
				System.out.println("Enter Email ID");
				emailID = br.readLine();

				System.out.println("Enter Card Number");
				String cardNumber = br.readLine();

				System.out.println("Enter Name on Card");
				String cardName = br.readLine();

				System.out.println("Enter expiry");
				String expiry = br.readLine();
			}
			if (numberOfSeats != 0) {
				if (userID == 0) {
					if (rs != null) {
						int transactionID = 0;
						rs.close();
						query = "select MAX(transaction_id) from t_transaction_history";
						rs = st.executeQuery(query);
						rsmd = rs.getMetaData();
						colNumber = rsmd.getColumnCount();
						while (rs.next()) {
							transactionID = rs.getInt(1);
						}
						transactionID = transactionID + 1;
						rs.close();

						query = "Insert into t_transaction_history values ( '" + transactionID + "', ' " + emailID
								+ "', '" + showID + "','" + numberOfSeats + "')";
					}
				} else {
					if (rs != null) {
						rs.close();

						query = "Insert into t_booking_history values ( '" + userID + "', '" + showID + "','"
								+ numberOfSeats + "')";
					}
				}
				int rowsAffected = st.executeUpdate(query);
				if (rowsAffected == 1) {
					System.out.println("Booking Successfully done..");
				} else {
					System.out.println("Booking Not Successful.");
				}
			}
		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static void option2() throws Exception {
		int userID = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Are you Registered User");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int user = Integer.parseInt(br.readLine());
		if (user == 2) {
			System.out.println("Do you want to register?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			int register = Integer.parseInt(br.readLine());
			if (register == 1) {
				userID = register();
			}
			if (register == 2) {
				userID = 0;
				bookTicket(userID);
			}
		}
		if (user == 1) {
			userID = login();
			if (userID != 0) {
				bookTicket(userID);
			}
		}
	}

	public static void addCredits(int userID, String today) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String query = null;
		String insertValues = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int colNumber = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			query = "Insert Into t_credits_earned Values ( ' " + userID + "', to_timestamp( '" + today
					+ "','dd/mm/yyyy hh24:mi'),'2','3')";

			rs = st.executeQuery(query);
			if (rs.next()) {
				System.out.println("Points Added");
			} else {
				System.out.println("Error while adding points ");
			}
			rs.close();

			System.out.println("");
			rs.close();
			st.close();

			st = con.createStatement();
			rs = st.executeQuery(insertValues);
			if (rs.next()) {
				System.out.println("Inserted Successfully ");
			} else {
				System.out.println("Insert not Successful ");
			}

		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static String forum(int userID) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String query = null;
		String today = null;
		String deleteValues = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int colNumber = 0, forumID = 0, choice = 0;
		try {
			con = getConnection();
			st = con.createStatement();

			query = "SELECT * FROM t_forum";

			rs = st.executeQuery(query);
			rsmd = rs.getMetaData();
			colNumber = rsmd.getColumnCount();

			for (int i = 1; i <= colNumber; i++) {
				if (i >= 1) {
					System.out.print("|");
					String columnValue = rsmd.getColumnName(i);
					System.out.print(columnValue + "\t\t");
				}
			}
			System.out.println("");

			while (rs.next()) {
				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rs.getString(i);
						System.out.print(columnValue + "\t\t\t");
					}
				}
				System.out.println("");
			}
			rs.close();

			System.out.println("");

			if (userID != 0) {
				// user
				int forumCreated = 0, CommentAdded = 0;
				System.out.println("1. Create Thread");
				System.out.println("2. Comment on a Thread");
				int threadInput = Integer.parseInt(br.readLine());
				switch (threadInput) {
				case 1:
					query = "select MAX(forum_id) from t_forum";
					rs = st.executeQuery(query);
					rsmd = rs.getMetaData();
					colNumber = rsmd.getColumnCount();
					while (rs.next()) {
						forumID = rs.getInt(1);
					}
					forumID = forumID + 1;
					rs.close();

					System.out.println("Enter Forum title");
					String forumTitle = br.readLine();

					System.out.println("Enter Forum type");
					String forumType = br.readLine();

					System.out.println("Enter Date and Time (dd/mm/yyyy hh24:mi)");
					today = br.readLine();

					query = "Insert into t_forum values ( '" + forumID + "','" + forumTitle + "','" + userID + "','"
							+ forumType + "')";

					rs = st.executeQuery(query);
					if (rs.next()) {
						System.out.println("Forum Created Successfully ");
						forumCreated = 1;
					} else {
						System.out.println("Forum not Created ");
					}
					rs.close();
					st.close();
					con.close();

					con = getConnection();
					st = con.createStatement();

					if (forumCreated == 1) {
						query = "Insert Into t_credits_earned Values ( ' " + userID + "', to_timestamp( '" + today
								+ "','dd/mm/yyyy hh24:mi'),'2','3')";

						rs = st.executeQuery(query);
						if (rs.next()) {
							System.out.println("Points Added");
						} else {
							System.out.println("Error while adding points ");
						}
						rs.close();
					}

					break;
				case 2:
					System.out.println("Enter forumID");
					forumID = Integer.parseInt(br.readLine());

					query = "SELECT * FROM t_forum_replies where forum_id = " + forumID;
					rs = st.executeQuery(query);
					rsmd = rs.getMetaData();
					colNumber = rsmd.getColumnCount();

					for (int i = 1; i <= colNumber; i++) {
						if (i >= 1) {
							System.out.print("|");
							String columnValue = rsmd.getColumnName(i);
							System.out.print(columnValue + "\t\t");
						}
					}
					System.out.println("");

					while (rs.next()) {
						for (int i = 1; i <= colNumber; i++) {
							if (i >= 1) {
								System.out.print("|");
								String columnValue = rs.getString(i);
								System.out.print(columnValue + "\t\t\t");
							}
						}
						System.out.println("");
					}
					rs.close();

					System.out.println("Enter your comment");
					String comment = br.readLine();

					System.out.println("Enter Date and Time (dd/mm/yyyy hh24:mi)");
					today = br.readLine();

					query = "Insert Into t_forum_replies Values ( '" + forumID + "','" + userID + "', to_timestamp( '"
							+ today + "','dd/mm/yyyy hh24:mi'),'" + comment + "')";

					rs = st.executeQuery(query);
					if (rs.next()) {
						System.out.println("Comment Added Successfully ");
						CommentAdded = 1;
					} else {
						System.out.println("Comment Cannot be added ");
					}

					rs.close();

					if (CommentAdded == 1) {
						// addCredits(userID,today,1,4);
						query = "Insert Into t_credits_earned Values ( '" + userID + "', to_timestamp( '" + today
								+ "','dd/mm/yyyy hh24:mi'),'1','4')";
						// System.out.println(query);
						rs = st.executeQuery(query);
						if (rs.next()) {
							System.out.println("Points Added");
						} else {
							System.out.println("Error while adding points ");
						}
						rs.close();
					}

					break;
				}
			} else {
				// guest - need to display comments
				System.out.println("Enter forumID");
				forumID = Integer.parseInt(br.readLine());

				query = "SELECT * FROM t_forum_replies";
				rs = st.executeQuery(query);
				rsmd = rs.getMetaData();
				colNumber = rsmd.getColumnCount();

				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rsmd.getColumnName(i);
						System.out.print(columnValue + "\t\t");
					}
				}
				System.out.println("");

				while (rs.next()) {
					for (int i = 1; i <= colNumber; i++) {
						if (i >= 1) {
							System.out.print("|");
							String columnValue = rs.getString(i);
							System.out.print(columnValue + "\t\t\t");
						}
					}
					System.out.println("");
				}
				rs.close();
			}

		} catch (SQLException SQLE) {
			SQLE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return today;
	}

	public static void option3() throws Exception {
		int userID = 0;
		String today = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Are you Registered User");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int user = Integer.parseInt(br.readLine());
		if (user == 2) {
			System.out.println("Do you want to register?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			int register = Integer.parseInt(br.readLine());
			if (register == 1) {
				userID = register();
			}
			if (register == 2) {
				userID = 0;
				today = forum(userID);
				addCredits(userID, today);
			}
		}
		if (user == 1) {
			userID = login();
			// userID = 1;
			if (userID != 0) {
				today = forum(userID);
				addCredits(userID, today);
			}
		}
	}

	public static void option4() throws Exception {
		int userSelection = 0;
		String query = null, updateValues = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int colNumber = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1.  Display the 3 most recent discussions/comments from a specific discussion thread.");
		System.out.println("2.  Display the 3 most recent discussion/comments from all discussion threads.");
		System.out.println("3.  Display the least popular discussion thread in terms of visits and comments.");
		System.out.println("4.  Display an alert to a registered guest when his membership status has changed.");
		System.out.println("5.	Display the registered guest who has contributed most comments.");
		System.out.println("6.	Display the theatre(s) that are showing most number of movies.");
		System.out.println("7.	Display the theatre (s) that has the most online ticket sales.");
		System.out.println(
				"8.	Display the list of all employees who are on duty on Monday on a specific theatre. Display also their jobs and time table.");
		System.out.println(
				"9.	Send an alert to the owner and manager if no employee with the job of security is scheduled to work tomorrow.");

		System.out.println("Press any other Number to exit");
		userSelection = Integer.parseInt(br.readLine());
		switch (userSelection) {
		case 1:
			int forumID = 0;
			try {
				System.out.println("Enter forumID");
				forumID = Integer.parseInt(br.readLine());
				con = getConnection();
				st = con.createStatement();
				// String tableName = selectTable();
				query = " SELECT TFR.DESCRIPTION " + "FROM T_Forum                  TF "
						+ "JOIN T_FORUM_REPLIES          TFR " + "ON TF.FORUM_ID = TFR.FORUM_ID " + "WHERE ROWNUM <=3 "
						+ "AND TF.FORUM_ID = " + forumID + "ORDER BY TFR.FORUM_DATE_TIME";
				rs = st.executeQuery(query);
				rsmd = rs.getMetaData();
				colNumber = rsmd.getColumnCount();

				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rsmd.getColumnName(i);
						System.out.print(columnValue + "\t\t");
					}
				}
				System.out.println("");

				while (rs.next()) {
					for (int i = 1; i <= colNumber; i++) {
						if (i >= 1) {
							System.out.print("|");
							String columnValue = rs.getString(i);
							System.out.print(columnValue + "\t\t\t");
						}
					}
					System.out.println("");
				}
				rs.close();
			} catch (SQLException SQLE) {
				SQLE.printStackTrace();
			} catch (Exception E) {
				E.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			}
			break;
		case 2:

			try {
				con = getConnection();
				st = con.createStatement();
				// String tableName = selectTable();
				query = " SELECT TFR.DESCRIPTION " + "FROM T_Forum                  TF "
						+ "JOIN T_FORUM_REPLIES          TFR " + "ON TF.FORUM_ID = TFR.FORUM_ID " + "WHERE ROWNUM <=3 "
						+ "ORDER BY TFR.FORUM_DATE_TIME";
				rs = st.executeQuery(query);
				rsmd = rs.getMetaData();
				colNumber = rsmd.getColumnCount();

				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rsmd.getColumnName(i);
						System.out.print(columnValue + "\t\t");
					}
				}
				System.out.println("");

				while (rs.next()) {
					for (int i = 1; i <= colNumber; i++) {
						if (i >= 1) {
							System.out.print("|");
							String columnValue = rs.getString(i);
							System.out.print(columnValue + "\t\t\t");
						}
					}
					System.out.println("");
				}
				rs.close();
			} catch (SQLException SQLE) {
				SQLE.printStackTrace();
			} catch (Exception E) {
				E.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			}
			break;
		case 3:
			try {

				con = getConnection();
				st = con.createStatement();
				// String tableName = selectTable();
				query = "  SELECT TF.FORUM_TITLE " + "FROM (SELECT FORUM_ID, COUNT(*) " + "FROM T_FORUM_REPLIES "
						+ "WHERE ROWNUM = 1 " + "GROUP BY FORUM_ID " + "ORDER BY COUNT(*)) A "
						+ "JOIN T_Forum              TF " + "ON TF.FORUM_ID = A.FORUM_ID";
				rs = st.executeQuery(query);
				rsmd = rs.getMetaData();
				colNumber = rsmd.getColumnCount();

				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rsmd.getColumnName(i);
						System.out.print(columnValue + "\t\t");
					}
				}
				System.out.println("");

				while (rs.next()) {
					for (int i = 1; i <= colNumber; i++) {
						if (i >= 1) {
							System.out.print("|");
							String columnValue = rs.getString(i);
							System.out.print(columnValue + "\t\t\t");
						}
					}
					System.out.println("");
				}
				rs.close();
			} catch (SQLException SQLE) {
				SQLE.printStackTrace();
			} catch (Exception E) {
				E.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			}
			break;

		case 4:
			int input4 = 0;
			int userID = 0, updatedID = 0;
			while (input4 != 3) {
				System.out.println("1. Owner/Manager Login");
				System.out.println("2. Customer Login");
				System.out.println("3. Exit");
				int input41 = Integer.parseInt(br.readLine());

				if (input41 == 1) {
					userID = login();
					if (userID != 0) {
						System.out.println("Enter userID change membership");
						updatedID = Integer.parseInt(br.readLine());

						System.out.println("Enter new membership");
						int membership = Integer.parseInt(br.readLine());

						try {
							con = getConnection();
							st = con.createStatement();
							// rsmd = rs.getMetaData();
							// colNumber = rsmd.getColumnCount();
							updateValues = "Update t_userdetails SET membership_id = '" + membership + "' "
									+ " where user_id = '" + updatedID + "'";
							rs = st.executeQuery(updateValues);
							if (rs.next()) {
								System.out.println("Updated Successfully ");
							} else {
								System.out.println("Update not Successful ");
							}
							rs.close();
						} catch (SQLException SQLE) {
							SQLE.printStackTrace();
						} catch (Exception E) {
							E.printStackTrace();
						} finally {
							if (rs != null) {
								rs.close();
							}
							if (st != null) {
								st.close();
							}
							if (con != null) {
								con.close();
							}
						}
					}
				}

				if (input41 == 2) {
					userID = login();
					if (userID == updatedID) {
						System.out.println("Membership updated");
					}
				}

				if (input41 == 3) {
					input4 = 3;
				}
			}
			break;

		case 5:
			try {
				con = getConnection();
				st = con.createStatement();
				// String tableName = selectTable();
				query = "SELECT USER_ID, " + "COUNT(FORUM_ID) AS COMMENT_COUNT " + "FROM T_FORUM_REPLIES "
						+ "GROUP BY USER_ID " + "HAVING COUNT( FORUM_ID) = (SELECT MAX(C) "
						+ "FROM (SELECT COUNT(*) AS C " + "FROM T_FORUM_REPLIES " + "GROUP BY USER_ID))";
				rs = st.executeQuery(query);
				rsmd = rs.getMetaData();
				colNumber = rsmd.getColumnCount();

				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rsmd.getColumnName(i);
						System.out.print(columnValue + "\t\t");
					}
				}
				System.out.println("");

				while (rs.next()) {
					for (int i = 1; i <= colNumber; i++) {
						if (i >= 1) {
							System.out.print("|");
							String columnValue = rs.getString(i);
							System.out.print(columnValue + "\t\t\t");
						}
					}
					System.out.println("");
				}
				rs.close();
			} catch (SQLException SQLE) {
				SQLE.printStackTrace();
			} catch (Exception E) {
				E.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			}
			break;

		case 6:
			try {

				con = getConnection();
				st = con.createStatement();
				// String tableName = selectTable();
				query = "SELECT THEATER_ID, " + "COUNT(SHOW_ID) AS MOST_NO_OF_MOVIES " + "FROM T_SHOWS "
						+ "GROUP BY THEATER_ID " + "HAVING COUNT( SHOW_ID) = (SELECT MAX(C) "
						+ "FROM (SELECT COUNT(*) AS C " + "FROM T_SHOWS " + "GROUP BY THEATER_ID))";
				rs = st.executeQuery(query);
				rsmd = rs.getMetaData();
				colNumber = rsmd.getColumnCount();

				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rsmd.getColumnName(i);
						System.out.print(columnValue + "\t\t");
					}
				}
				System.out.println("");

				while (rs.next()) {
					for (int i = 1; i <= colNumber; i++) {
						if (i >= 1) {
							System.out.print("|");
							String columnValue = rs.getString(i);
							System.out.print(columnValue + "\t\t\t");
						}
					}
					System.out.println("");
				}
				rs.close();
			} catch (SQLException SQLE) {
				SQLE.printStackTrace();
			} catch (Exception E) {
				E.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			}
			break;

		case 7:
			try {

				con = getConnection();
				st = con.createStatement();
				// String tableName = selectTable();
				query = "SELECT THEATER_ID, " + "THEATRE_HIGHEST " + "FROM (SELECT TS.THEATER_ID, "
						+ "SUM(TBH.NUMBER_OF_SETAS) AS THEATRE_HIGHEST, "
						+ "MAX(SUM(THEATRE_HIGHEST)) OVER() AS MAX_THEATRE_HIGHEST "
						+ "FROM T_BOOKING_HISTORY           TBH " + "JOIN T_SHOWS                     TS "
						+ "ON TBH.SHOW_ID = TS.SHOW_ID " + "GROUP BY TS.THEATER_ID) "
						+ "WHERE THEATRE_HIGHEST = MAX_THEATRE_HIGHEST";
				rs = st.executeQuery(query);
				rsmd = rs.getMetaData();
				colNumber = rsmd.getColumnCount();

				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rsmd.getColumnName(i);
						System.out.print(columnValue + "\t\t");
					}
				}
				System.out.println("");

				while (rs.next()) {
					for (int i = 1; i <= colNumber; i++) {
						if (i >= 1) {
							System.out.print("|");
							String columnValue = rs.getString(i);
							System.out.print(columnValue + "\t\t\t");
						}
					}
					System.out.println("");
				}
				rs.close();
			} catch (SQLException SQLE) {
				SQLE.printStackTrace();
			} catch (Exception E) {
				E.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			}
			break;

		case 8:
			System.out.println("Enter Zipcode");
			int zipcode = Integer.parseInt(br.readLine());
			try {
				con = getConnection();
				st = con.createStatement();
				// String tableName = selectTable();
				query = "SELECT TSA.THEATER_ID, " + "TSA.STAFF_ID, " + "TSD.STAFF_FIRST_NAME, " + "TSA.FROM_TIME, "
						+ "TSA.TO_TIME, " + "SSF.FUNCTION_TYPE " + "FROM T_STAFF_ALLOCATION          TSA "
						+ "JOIN S_THEATER                   ST " + "ON TSA.THEATER_ID = ST.THEATER_ID "
						+ "JOIN T_STAFF_DETAILS             TSD " + "ON TSD.STAFF_ID = TSA.STAFF_ID "
						+ "JOIN S_STAFF_FUNCTION            SSF " + "ON TSD.FUNCTION_ID = SSF.FUNCTION_ID "
						+ "WHERE ST.ZIPCODE = '" + zipcode + "' " + "AND TO_CHAR(TSA.FROM_TIME, 'D') = 2";
				rs = st.executeQuery(query);
				rsmd = rs.getMetaData();
				colNumber = rsmd.getColumnCount();
 
				for (int i = 1; i <= colNumber; i++) {
					if (i >= 1) {
						System.out.print("|");
						String columnValue = rsmd.getColumnName(i);
						System.out.print(columnValue + "\t\t");
					}
				}
				System.out.println("");

				while (rs.next()) {
					for (int i = 1; i <= colNumber; i++) {
						if (i >= 1) {
							System.out.print("|");
							String columnValue = rs.getString(i);
							System.out.print(columnValue + "\t\t\t");
						}
					}
					System.out.println("");
				}
				rs.close();
			} catch (SQLException SQLE) {
				SQLE.printStackTrace();
			} catch (Exception E) {
				E.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			}
			break;

		case 9:

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();

			System.out.println(dateFormat.format(date)); // 2014/08/06 15:59:48
			break;
		}
	}

	public static void option5() throws Exception {
		int userSelection = 0, userID = 0, colNumber = 0;
		String query = null, updateValues = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(
				"1. Show that a registered guest can view his own information; change his phone number but not his membership status nor his reward points.");
		System.out.println(
				"2. Show that only the guest himself, the owner and the web administrator can view the guest’s information.");
		System.out.println(
				"3. Show that the owner can delegate the updating of movies to be shown and their show time to the manager of a theatre.");
		userSelection = Integer.parseInt(br.readLine());
		switch (userSelection) {
		case 1:
			userID = login();
			if (userID != 0) {
				System.out.println("Enter Phone Number");
				String phone = br.readLine();

				try {
					con = getConnection();
					st = con.createStatement();
					// rsmd = rs.getMetaData();
					// colNumber = rsmd.getColumnCount();
					updateValues = "Update t_userdetails SET phone_number = '" + phone + " where user_id = '" + userID
							+ "'";
					rs = st.executeQuery(updateValues);
					if (rs.next()) {
						System.out.println("Updated Successfully ");
					} else {
						System.out.println("Update not Successful ");
					}
					rs.close();
				} catch (SQLException SQLE) {
					SQLE.printStackTrace();
				} catch (Exception E) {
					E.printStackTrace();
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
				}
			}

			break;
		case 2:
			System.out.println("Login As");
			System.out.println("1. Owner");
			System.out.println("2. Web admin");
			System.out.println("3. Customer");
			int input2 = Integer.parseInt(br.readLine());

			userID = login();
			if (userID != 0) {
				try {
					con = getConnection();
					st = con.createStatement();
//					String tableName = selectTable();
					query = "SELECT * FROM t_userdetails Where user_id" + userID;

					rs = st.executeQuery(query);
					rsmd = rs.getMetaData();
					colNumber = rsmd.getColumnCount();

					for (int i = 1; i <= colNumber; i++) {
						if (i >= 1) {
							System.out.print("|");
							String columnValue = rsmd.getColumnName(i);
							System.out.print(columnValue + "\t\t");
						}
					}
					System.out.println("");

					while (rs.next()) {
						for (int i = 1; i <= colNumber; i++) {
							if (i >= 1) {
								System.out.print("|");
								String columnValue = rs.getString(i);
								System.out.print(columnValue + "\t\t\t");
							}
						}
						System.out.println("");
					}
					System.out.println("");
					System.out.println("");
					rs.close();

				} catch (SQLException SQLE) {
					SQLE.printStackTrace();
				} catch (Exception E) {
					E.printStackTrace();
				} finally {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
				}
			}

			break;
		case 3:
			int input = 0;
			while (input != 3) {
				System.out.println("Manager Login");
				userID = login();
				if (userID != 0) {
					System.out.println("1. Update Shows");
					int stop = 0, choice = 0;
					try {
						con = getConnection();
						st = con.createStatement();
						// String tableName = selectTable();
						query = "SELECT * FROM t_shows";

						rs = st.executeQuery(query);
						rsmd = rs.getMetaData();
						colNumber = rsmd.getColumnCount();
						updateValues = "Update t_shows SET ";

						for (int i = 1; i <= colNumber; i++) {
							if (i >= 1) {
								String columnValue = rsmd.getColumnName(i);
								System.out.println(columnValue);
							}
						}

						while (stop == 0) {
							System.out.println("Enter the SET column Name");
							updateValues = updateValues + br.readLine();
							System.out.println("Enter the Column Value");
							updateValues = updateValues + " = " + br.readLine();
							System.out.println("do you want to set values for more columns");
							System.out.println("Yes (0) / No (1)");
							stop = Integer.parseInt(br.readLine());
							if (stop == 0) {
								updateValues = updateValues + ", ";
							}
						}

						stop = 0;
						updateValues = updateValues + " WHERE ";

						while (stop == 0) {
							System.out.println("Enter the WHERE column Name");
							updateValues = updateValues + br.readLine();
							System.out.println("Enter the Column Value");
							updateValues = updateValues + " = " + br.readLine();
							System.out.println("do you want to set more WHERE Conditions");
							System.out.println("Yes (0) / No (1)");
							stop = Integer.parseInt(br.readLine());
							if (stop == 0) {
								System.out.println("Select AND (1) or OR (2) Condition");
								choice = Integer.parseInt(br.readLine());
								if (choice == 1) {
									updateValues = updateValues + " AND ";
								}
								if (choice == 2) {
									updateValues = updateValues + " OR ";
								}
							}
						}

						System.out.println("");
						rs.close();
						st.close();

						st = con.createStatement();
						rs = st.executeQuery(updateValues);
						if (rs.next()) {
							System.out.println("Updated Successfully ");
						} else {
							System.out.println("Update not Successful ");
						}

					} catch (SQLException SQLE) {
						SQLE.printStackTrace();
					} catch (Exception E) {
						E.printStackTrace();
					} finally {
						if (rs != null) {
							rs.close();
						}
						if (st != null) {
							st.close();
						}
						if (con != null) {
							con.close();
						}
					}
				}
			}
			break;
		}
	}

	public static void selectOption() throws Exception {
		int userSelection = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("");
		System.out.println("");
		System.out.println("Select one of the option:");
		System.out.println("1. SELECT, INSERT, UPDATE, DELETE");
		System.out.println("2. Purchase Ticket");
		System.out.println("3. Forums");
		System.out.println("4. Queries");
		System.out.println("5. Authorizations");
		System.out.println("Press any other Number to exit");
		userSelection = Integer.parseInt(br.readLine());

		switch (userSelection) {
		case 1:
			option1();
			selectOption();
			break;
		case 2:
			option2();
			selectOption();
			break;
		case 3:
			option3();
			selectOption();
			break;
		case 4:
			option4();
			selectOption();
			break;
		case 5:
			option5();
			break;
		}
	}

	public static void main(String args[]) throws Exception {
		System.out.println("Welcome to Theater amangement system");
		selectOption();
	}
}
