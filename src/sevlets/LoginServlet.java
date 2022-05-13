package sevlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		Connection con = null;
		Statement stmt = null, stmt1 = null;
		ResultSet rs = null, rs1 = null;
		ArrayList loginlist = new ArrayList();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;DatabaseName=student", "sa", "123");
			stmt = con.createStatement();
			String sql="select * from info where  stu_name='" + user
					+ "' and stu_pass='" + pass + "'";
			
			rs = stmt.executeQuery(sql);
			if (rs.getRow()==0) {
				
				stmt1 = con.createStatement();
				rs1 = stmt.executeQuery("select * from info ");
				while (rs1.next()) {
					
					login login = new login();
					
					login.setName(rs1.getString("stu_name"));
					
					login.setPassword(rs1.getString("stu_pass"));
					login.setSex(rs1.getString("stu_sex"));
					login.setAge(rs1.getString("stu_age"));
					login.setNclass(rs1.getString("stu_class"));
					loginlist.add(login);

				}
				session.setAttribute("loginlist", loginlist);
			}else{
				session.setAttribute("user", user);
				response.sendRedirect("login.jsp");
			}
			rs1.close();
			rs.close();
			stmt1.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("mysql.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
