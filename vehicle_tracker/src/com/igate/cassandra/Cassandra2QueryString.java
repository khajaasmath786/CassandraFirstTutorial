package com.igate.cassandra;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

/**
 * Servlet implementation class Cassandra2QueryString
 */
@WebServlet("/Cassandra2QueryString")
public class Cassandra2QueryString extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cassandra2QueryString() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
		Session session = cluster.connect();
				PrintWriter out = response.getWriter();
				out.println("Vehicle Tracker");
				String vehicleId = "CA6AFL218";
				String trackDate = "2014-05-19";
				String queryString = "SELECT time, latitude, longitude FROM vehicle_tracker.location WHERE vehicle_id = '" + vehicleId + "' AND date = '" + trackDate + "'";
				ResultSet result = session.execute(queryString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
