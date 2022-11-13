package com.soen.app.disney;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryManager {

	Connection con = null;

	public void createConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/disney_characters?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false&allowPublicKeyRetrieval=true",
					"root", "root");
		} catch (Exception e) {
			System.out.println("Error while connecting to database");
		}
	}

	public Boolean executeCreateQuery(String query) {
		Boolean isQueryExecuted = false;
		try {
			Statement smt = con.createStatement();
			smt.executeUpdate(query);
			isQueryExecuted = true;
		} catch (Exception e) {
			System.out.println("Error while executing statement " + e.getMessage());
		}
		return isQueryExecuted;
	}

	public ResultSet executeQuery(String query) {
		ResultSet resultSet = null;
		try {
			Statement smt = con.createStatement();
			resultSet = smt.executeQuery(query);

		} catch (Exception e) {
			System.out.println("Error while executing statement " + e.getMessage());
		}
		return resultSet;
	}

	public Boolean executeUpdateQuery(String query) {
		Boolean isQueryExecuted = false;
		try {
			Statement smt = con.createStatement();
			smt.executeUpdate(query);
			isQueryExecuted = true;
		} catch (Exception e) {
			System.out.println("Error while executing statement " + e.getMessage());
		}
		return isQueryExecuted;
	}

}
