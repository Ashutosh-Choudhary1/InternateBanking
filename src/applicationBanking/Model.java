package applicationBanking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Model
 */
public class Model extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//
	// instance variable
	static String url = "jdbc:oracle:thin:@//localhost:1521/XE";
	static String user = "system";
	static String pass = "system";
	PreparedStatement pstmt = null;
	ResultSetMetaData rsmd = null;
	ResultSet res = null;
	Connection con = null;

	//
	private String name;
	private int accountnumber;
	private int balance;
	private String customerid;
	private String password;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getBalance() {
		return balance;
	}


	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// connection database
	/*
	 * public static Connection getConnection() {
	 * 
	 * try { Class.forName("oracle.jdbc.driver.OracleDriver");
	 * con=DriverManager.getConnection(url, user, pass); } catch(Exception e) {
	 * e.printStackTrace(); } return con; }
	 */
	public Model() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	public boolean register() {

		String s = "insert into bank values(?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, name);
			pstmt.setInt(2, accountnumber);
			pstmt.setInt(3, balance);
			pstmt.setString(4, customerid);
			pstmt.setString(5, password);
			pstmt.setString(6, email);
			int val = pstmt.executeUpdate();
			if (val > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// for login by customer id and password
	public boolean internetBanking() {
		// call database getConnection method
		String s = "select * from bank where customerid=? and password=?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, customerid);
			pstmt.setString(2, password);
			res = pstmt.executeQuery();
			if (res.next() == true) {
				accountnumber = res.getInt(2);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	// for checking balance
	public boolean fetchBalance() {

		String s = "select * from bank where accountnumber=?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, accountnumber);
			res = pstmt.executeQuery();
			if (res.next() == true) {
				balance = res.getInt(3);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// change password
	public boolean changePass() {

		String s = "update bank set password=? where accountnumber=?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, password);
			pstmt.setInt(2, accountnumber);
			int val = pstmt.executeUpdate();
			if (val > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// transfer amount method
	public boolean transferAmount(String amount, String toaccount) {

		String s = "update bank set balance=balance-? where accountnumber=?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, amount);
			pstmt.setInt(2, accountnumber);
			int val = pstmt.executeUpdate();
			if (val > 0) {
				System.out.println("working");
				String s1 = "update bank set balance=balance+? where accountnumber=?";
				pstmt = con.prepareStatement(s1);
				pstmt.setString(1, amount);
				pstmt.setString(2, toaccount);
				int val1 = pstmt.executeUpdate();
				if (val1 > 0){
					String s3="insert into getstatement values(?,?,?,?)";
					pstmt=con.prepareStatement(s3);
					pstmt.setInt(1,accountnumber);
					pstmt.setString(2,amount);
					pstmt.setString(3,toaccount);
					Date d=new Date();
					int date=d.getDate();
					int month=d.getMonth();
					int year=d.getYear();
					String finaldate=date+"/"+month+"/"+year;
					pstmt.setString(4,finaldate);
					pstmt.executeUpdate();
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Apply loan
	public boolean applyLoan() {

		String s = "select * from bank where accountnumber=?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, accountnumber);
			res = pstmt.executeQuery();
			if (res.next() == true) {
				email = res.getString(6);
				name = res.getString(1);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList getStatement() {
		ArrayList al=new ArrayList();
		String s="select * from getstatement where accountnumber=?";
		try {
			pstmt=con.prepareStatement(s);
			pstmt.setInt(1,accountnumber);
			res=pstmt.executeQuery();
			while(res.next()==true) {
				accountnumber=res.getInt(1);
				int amount=res.getInt(2);
				int toaccount=res.getInt(3);
				String date=res.getString(4);
				al.add(accountnumber+","+amount+","+toaccount+","+date);
			}
			return al;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}

}
