package com.dwb.stuffoflegend.database.core;

import static com.dwb.stuffoflegend.database.core.QueryKey.GET_PROGRESSIONS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.dwb.stuffoflegend.data.Progression;

public class ProgressionDatabaseInteractor extends DatabaseInteractor {
	
	Map<Integer, Progression> progressions = new HashMap<>();

	public Map<Integer, Progression> getProgressionsList() {
		if (progressions.isEmpty()) {
			Map<String, String> parameters = new HashMap<>();
			ResultSet resultSet = executeQuery(GET_PROGRESSIONS, parameters);
			try {
				while (resultSet.next()) {

					Progression progression = new Progression(
							resultSet.getString("name"),
							resultSet.getInt("offset"));
					progressions.put(resultSet.getInt("id"), progression);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return progressions;
	}
	
	public Progression getProgressionById(int id) {
		return getProgressionsList().get(id);
	}

	@Override
	public void clearCache() {
		progressions.clear();
	}
}
