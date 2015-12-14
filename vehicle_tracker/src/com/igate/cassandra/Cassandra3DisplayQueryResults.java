package com.igate.cassandra;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Servlet implementation class Cassandra3DisplayQueryResults
 */
@WebServlet("/display")
public class Cassandra3DisplayQueryResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cassandra3DisplayQueryResults() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
		Session session = cluster.connect();
				PrintWriter out = response.getWriter();
				out.println("Vehicle Tracker");
				String vehicleId = "CA6AFL218";
				String trackDate = "2014-05-19";
				String queryString = "SELECT time, latitude, longitude FROM vehicle_tracker.location WHERE vehicle_id = '" + vehicleId + "' AND date = '" + trackDate + "'";
				ResultSet result = session.execute(queryString);
				
				out.println("<table>");
				for (Row row : result)
				{
				   out.println("<tr>");
				   out.println("<td>" + row.getDate("time") + "</td>");
				   out.println("<td>" + row.getDouble("latitude") + "</td>");
				   out.println("<td>" + row.getDouble("longitude") + "</td>");   
				   out.println("</tr>");
				}
				out.println("</table>");
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
