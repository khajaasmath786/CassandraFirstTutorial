package com.igate.cassandra;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 * Servlet implementation class Cassandra1PrintWriter
 */
@WebServlet("/printWriter")
public class Cassandra1PrintWriter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cassandra1PrintWriter() {
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
	}

}
