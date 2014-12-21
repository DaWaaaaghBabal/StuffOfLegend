package com.dwb.stuffoflegend.database.core;

import java.sql.ResultSet;

public interface DatabaseConnector {

	public ResultSet executeQuery(String query);
	public void openConnection();
	public void closeConnection();
}
