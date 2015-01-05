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
	public int executeUpdate(String query) {
		try {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void openConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String login = "stuffoflegend";
				String password = "stuffoflegend";
				String url = "jdbc:mysql://localhost:3306/stuffoflegend";
				connection = DriverManager.getConnection(url, login, password);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeConnection() {
		try {
			if(connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
