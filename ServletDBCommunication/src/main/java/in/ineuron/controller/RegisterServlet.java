package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
		urlPatterns = { "/reg" }, 
		initParams = { 
				@WebInitParam(name = "url", value = "jdbc:mysql://localhost:3306/ineuron"), 
				@WebInitParam(name = "user", value = "root"), 
				@WebInitParam(name = "password", value = "Password")
		})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	int rowCount = 0;
	
			
	static {
		System.out.println("RegisterServlet is loading...");
	}
	public RegisterServlet() {
		System.out.println("RegisterServlet is instantiating...");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("RegisterServlet is initializing...");
		ServletConfig config = getServletConfig();
		String url = config.getInitParameter("url");
		String user = config.getInitParameter("user");
		String password = config.getInitParameter("password");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch(ClassNotFoundException cfe) {
			cfe.printStackTrace();
		}
		
		catch (SQLException se) {
			se.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//getting the data from request object 
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		
		String sqlInsertQuery= "insert into students values(?,?,?,?)";
		PreparedStatement pstm = null;
		
		//using JDBC API code to send the data to database
		try {
			if(connection!=null)
			pstm = connection.prepareStatement(sqlInsertQuery);
			if(pstm!=null) {
				pstm.setString(1,name);
				pstm.setString(2,email);
				pstm.setString(3,city);
				pstm.setString(4,country);
				
				rowCount = pstm.executeUpdate();
				
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//prepare a response and send it to the end user.
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Output page</title></head>");
		out.println("<body>");
		if(rowCount==1)
			out.println("<h1 style='color:green;text-align:center;'>Registered Successful</h1>");
		else
			{
			out.println("<h1 style='color:red;text-align:center;'>Registration Failed</h1>");
			out.println("<a style='text-align:center;' href='./register.html'>Register Again</a>");
			}
		out.println("</body>");
		out.println("</html>");
		
		out.close();
		
	}
	
	@Override
	public void destroy() {
		System.out.println("RegisterServlet is De-Instantiating...");
	}

}
