package com.ser;
//Import required java libraries
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

import com.db.Jdbc;

//Extend HttpServlet class
public class HelloWorld extends HttpServlet {
private static final long serialVersionUID = -3511949807619600072L;

public void init() throws ServletException {
   // Do required initialization
}

public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
   
   // Set response content type
   response.setContentType("text/html");
   String query = request.getParameter("query");
   // Actual logic goes here.
   PrintWriter out = response.getWriter();
//   out.println("<h1>" + query + "</h1>");
//   Vector<Vector<Object>> d;
   out.println("<head><title>Query</title>"
   		+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"
   		+ "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
   		+ "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>"
   		+ "</head>");
try {
	Jdbc jd = new Jdbc();
	Connection dbConnection = null;
	Statement statement = null;
	Vector<Vector<Object>> data = null;
	Vector<String> columnNames;
	//Vector<Vector<Object>> d1 = j3.main2(query);
	dbConnection = jd.getDBConnection();
	statement = jd.statementValue(dbConnection);
	ResultSet rs = jd.resultValue(statement, query);
	ResultSetMetaData metadata = jd.metaResultValue(rs);
	columnNames = jd.columnCountValue(metadata);
	int columnCount = columnNames.size();
	data = jd.temp(metadata, rs,columnCount);
	String errorMsg = jd.errorMessage();
	//int y = j3.columnCountValue(columnCount);
   out.println("<table class='table table-hover'>");
   for(int i=0;i<columnNames.size();i++){
	   out.println("<th>"+ columnNames.get(i) +"</th>");
   }
    for(int i=0;i<data.size();i++){
    	out.println("<tr>");
    	for(int j=0;j<columnCount;j++){
    		out.println("<td>"+ data.get(i).get(j) +"</td>");
    	}
    	out.println("</tr>");
    }
    out.println("<table>");
	//out.println("<h1>" + d1.get(0).get(0) + "</h1>");
    out.println("<p id="+errorMsg+"></p>");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

public void destroy() {
   // do nothing.
}
}