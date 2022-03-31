package com.jdbc.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Step 1: Set up the printwriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		// Step 2: Get a connection to the database
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USERNAME, Constants.PASSWORD);

			// Step 3: Create a SQL statements
			String sql = "select * from student";
			statement = connection.createStatement();

			// Step 4: Execute SQL query
			result = statement.executeQuery(sql);

			// Step 5: Process the result set
			while (result.next()) {
				String email = result.getString("email");
				out.println(email);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
