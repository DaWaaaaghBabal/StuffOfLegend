package com.dwb.stuffoflegend.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dwb.stuffoflegend.database.core.DatabaseConnector;

import java.sql.Statement;

public class MysqlDatabaseConnector implements DatabaseConnector {

	private Connection	connection;

	@Override
	public ResultSet executeQuery(String query) {

		ResultSet result = null;
		try {
			Statement statement = connection.createStatement();
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public void openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String login = "stuffoflegend";
			String password = "stuffoflegend";
			String url = "jdbc:mysql://localhost:3306/stuffoflegend";
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
