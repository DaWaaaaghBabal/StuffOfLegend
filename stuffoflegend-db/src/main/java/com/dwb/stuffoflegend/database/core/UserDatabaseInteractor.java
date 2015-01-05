package com.dwb.stuffoflegend.database.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.dwb.stuffoflegend.database.core.QueryKey.LOGIN;

import com.dwb.stuffoflegend.data.User;

public class UserDatabaseInteractor extends DatabaseInteractor {

	public User login(String login, String password) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("password", password);
		parameters.put("login", login);
		ResultSet resultSet = executeQuery(LOGIN, parameters);
		User user = new User();
		try {
			if (resultSet.next()) {
				populateUser(resultSet, user);
				closeConnection();
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return null;
	}

	private void populateUser(ResultSet resultSet, User user)
			throws SQLException {
		user.setId(resultSet.getInt("id"));
		user.setEmail(resultSet.getString("email"));
		user.setLogin(resultSet.getString("login"));
	}

	@Override
	public void clearCache() {
		// No cache to clear.
	}
}
