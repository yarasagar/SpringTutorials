package com.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Jdbc {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/TEST";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "sagar225";
	public String errorMsg;
	
	public Vector<Vector<Object>> main2(String selectTableSQL) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		Vector<Vector<Object>> data = null;
		Vector<String> columnNames;
		//String selectTableSQL = "SELECT * from customer";
		Jdbc jd = new Jdbc();
		dbConnection = jd.getDBConnection();
		statement = jd.statementValue(dbConnection);
		ResultSet rs = jd.resultValue(statement, selectTableSQL);
		ResultSetMetaData metadata = jd.metaResultValue(rs);
		columnNames = jd.columnCountValue(metadata);
		int columnCount = columnNames.size();
		data = jd.temp(metadata, rs,columnCount);
		return data;
	}

	public Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			errorMsg = e.getMessage();
			//System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			errorMsg = e.getMessage();
			//System.out.println(e.getMessage());
		}
		return dbConnection;
	}
	
	public Statement statementValue(Connection dbConnection){
		Statement stmt = null;
		try {
			stmt = dbConnection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errorMsg = e.getMessage();
			//e.printStackTrace();
		}
		return stmt;
	}
	
	public ResultSet resultValue(Statement statement,String selectTableSQL){
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(selectTableSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errorMsg = e.getMessage();
			//e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSetMetaData metaResultValue(ResultSet rs){
		ResultSetMetaData metaData = null;
		try {
			metaData = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errorMsg = e.getMessage();
			//e.printStackTrace();
		}
		return metaData;
	}
	
	public Vector<String> columnCountValue(ResultSetMetaData metaData) throws SQLException{
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }
	    return columnNames;
	}
	
	public Vector<Vector<Object>> temp(ResultSetMetaData metaData,ResultSet rs,int columnCount) throws SQLException{
		Vector<Vector<Object>> data;
	    data = new Vector<Vector<Object>>();
	    //int rowCount = 0;
	    while (rs.next()) {
	    	//rowCount++;
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    return data;

	}
	
	public String errorMessage(){
		return errorMsg;
	}
	
}