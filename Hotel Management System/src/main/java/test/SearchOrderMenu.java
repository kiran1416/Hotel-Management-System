package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchOrderMenu extends HttpServlet{
	Connection con;
	
	@Override
	public void init() throws ServletException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel123","root","sql123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name=req.getParameter("name");
		
		PreparedStatement pstmt;
		ResultSet rs;
		PrintWriter pw=resp.getWriter();
		
		String query="select * from display_menu where order_name=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			pw.print("<style> body { background-size: 100%;\r\n"
					+ "		background-image: url(\"https://cdn.pixabay.com/photo/2015/05/31/11/23/table-791167_1280.jpg\")}</style>");
			pw.print("<body>"
					+"<table  border='4' rules='all' cellpadding='70px' style='margin-top:5%;margin-left:30%;background-color:white;'>"
					+"<tr><td><center><h1>MENU CARD</h1></center>"
					+ "<table  border='4' rules='all' cellpadding='40px' style='margin-top:10%;margin-left:10%;background-color:white;'>");
			pw.print("<tr>");
			pw.print("<th>ORDER ID</th>");
			pw.print("<th>ORDER NAME</th>");
			pw.print("<th>PRICE</th>");
			pw.print("</tr>");
			while(rs.next()) {
				pw.print("<tr>");
				pw.print("<td>"+rs.getInt(1)+"</td>");
				pw.print("<td>"+rs.getString(2)+"</td>");
				pw.print("<td>"+rs.getDouble(3)+"</td>");
				pw.print("</tr>");
			}
			pw.print("</table>");
			pw.print("</table>");
			pw.print("</td>");
			pw.print("</tr>");
			pw.print("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
