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

@WebServlet("/addlink")
public class AddOrder extends HttpServlet{
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
		String quantity=req.getParameter("quantity");
		
		int quantity1=Integer.parseInt(quantity);
		
		PreparedStatement pstmt;
		
		String query="insert into add_order(order_name,quantity) values(?,?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(2, quantity1);
			int count=pstmt.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.print("<style> body { background-size: 100%;\r\n"
					+ "		background-image: url(\"https://cdn.pixabay.com/photo/2015/05/31/11/23/table-791167_1280.jpg\")}</style>");
			pw.print("<body>\r\n"
					+ "		<table  border='4' rules='all' cellpadding='70px' style='margin-top:10%;margin-left:20%;background-color:white;'>\r\n"
					+ "		<tr>\r\n"
					+ "		<td>\r\n"
					+ "		<h1 style='color:green';>ORDER ADDED SUCCESSFULLY</h1>\r\n"		
					+ "				<br>\r\n"
					+ "				\r\n"
					+ "				<a href=\"Home.html\"><button class=\"btn btn-primary\">OK</button></a>\r\n"
					+ "			</form>\r\n"
					+ "			</td>\r\n"
					+ "			<td><img src=\"https://media.istockphoto.com/id/545286388/photo/chinese-food-blank-background.jpg?s=2048x2048&w=is&k=20&c=HPa1SVNVFmshXfSNVOtfTmH4eoKer1uvadcrZO8kxqk=\" hight=\"500\" width=\"500\"></td>\r\n"
					+ "			</tr>\r\n"
					+ "			</table>\r\n"
					+ "		</div>\r\n"
					+ "</body>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
