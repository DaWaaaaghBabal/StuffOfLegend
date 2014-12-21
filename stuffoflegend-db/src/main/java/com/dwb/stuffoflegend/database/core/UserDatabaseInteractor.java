package com.dwb.stuffoflegend.database.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.dwb.stuffoflegend.database.core.Queries.QueryKey.LOGIN;

import com.dwb.stuffoflegend.data.User;

public class UserDatabaseInteractor extends DatabaseInteractor {

	public User login(String login, String password) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("password", password);
		parameters.put("login", login);
		String query = queries.getFormattedQuery(LOGIN, parameters);
		dbConnector.openConnection();
		ResultSet resultSet = dbConnector.executeQuery(query);
		User user = new User();

		try {
			if (resultSet.next()) {
				populateUser(resultSet, user);
				dbConnector.closeConnection();
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbConnector.closeConnection();
		return null;
	}

	private void populateUser(ResultSet resultSet, User user)
			throws SQLException {
		user.setId(resultSet.getInt("id"));
		user.setEmail(resultSet.getString("email"));
		user.setLogin(resultSet.getString("login"));
	}
}
