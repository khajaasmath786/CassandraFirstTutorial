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
 * Servlet implementation class Cassandra5Submit
 */
@WebServlet("/submitTable")
public class Cassandra5Submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cassandra5Submit() {
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
		
		String vehicleId = request.getParameter("veh_id");
		String trackDate = request.getParameter("date_val");
		String queryString = "SELECT time, latitude, longitude FROM vehicle_tracker.location WHERE vehicle_id = '" + vehicleId + "' AND date = '" + trackDate + "' LIMIT 1";
		ResultSet result = session.execute(queryString);
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\"><html>");
		out.println("<head><title>Track a Vehicle</title></head>");
		out.println("<body>");
		out.println("<h1>Track a Vehicle</h1>");
		out.println("Enter the track date and id of the vehicle you want to track");	
		out.println("<p>&nbsp;</p>");
		out.println("<form id=\"form1\" name=\"form1\" method=\"get\" action=\"\">");
		out.println("<table>");
		out.println("<tr><td>Date (e.g. 2014-05-19):</td>");
		out.println("<td><input type=\"text\" name=\"date_val\" id=\"date_val\"/></td></tr>");
		out.println("<tr><td>Vehicle id (e.g. FLN78197):</td>");
		out.println("<td><input type=\"text\" name=\"veh_id\" id=\"veh_id\" /></td></tr>");
		out.println("<tr><td></td><td><input type=\"submit\" name=\"submit\" id=\"submit\" value=\"Submit\"/></td></tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<p>&nbsp;</p>");
		
		if(request.getParameter("veh_id") == null)
		{
			// blank
		}
		else if(result.isExhausted())
		{
			out.println("<hr/>");
			out.println("<p>&nbsp;</p>");
			out.println("Sorry, no results for vehicle id " + request.getParameter("veh_id") + " for " + request.getParameter("date_val"));
		}		
		else
		{
			out.println("<hr/>");
			out.println("<table cellpadding=\"4\">");
			out.println("<tr><td colspan=\"3\"><h2>" + request.getParameter("veh_id") + "</h2></td></tr>");
			out.println("<tr><td><b>Date and Time</b></td><td><b>Latitude</b></td><td><b>Longitude</b></td></tr>");
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

		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
