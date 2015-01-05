package com.dwb.stuffoflegend.database.core;

import static com.dwb.stuffoflegend.database.core.QueryKey.CREATE_CLASS;
import static com.dwb.stuffoflegend.database.core.QueryKey.GET_CLASSES;
import static com.dwb.stuffoflegend.database.core.QueryKey.LAST_INSERTED_ID;
import static com.dwb.stuffoflegend.database.core.QueryKey.TRANSLATE_CLASS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.dwb.stuffoflegend.data.CharacterClass;

public class ClassDatabaseInteractor extends DatabaseInteractor {

	public Map<Integer, CharacterClass>	classes	= new HashMap<>();

	public Map<Integer, CharacterClass> getClasses() {
		if (classes.isEmpty()) {
			Map<String, String> parameters = new HashMap<>();
			ResultSet resultSet = executeQuery(GET_CLASSES, parameters);
			try {
				while (resultSet.next()) {
					String name = resultSet.getString("name");
					int id = resultSet.getInt("id");
					int hp = resultSet.getInt("hp");
					int skills = resultSet.getInt("skills");
					float bab = resultSet.getInt("bab");
					CharacterClass clazz = new CharacterClass(id, name, hp,
							skills, bab);
					classes.put(id, clazz);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return classes;
	}

	public void createClass(CharacterClass clazz) {
		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put("hp", "" + clazz.getHp());
		parameters.put("skills", "" + clazz.getSkills());
		parameters.put("bab", "" + clazz.getBab());
		int result = executeUpdate(CREATE_CLASS, parameters);
		if (result > 0) {
			ResultSet resultSet = executeQuery(LAST_INSERTED_ID, parameters);
			try {
				if (resultSet.next()) {
					clazz.setId(resultSet.getInt("id"));
					parameters.clear();
					parameters.put("id", "" + clazz.getId());
					parameters.put("name", clazz.getName());
					parameters.put("class", clazz.getName());
					executeUpdate(TRANSLATE_CLASS, parameters);
					classes.put(clazz.getId(), clazz);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Couldn't create class...");
		}
	}

	@Override
	public void clearCache() {
		classes.clear();
	}

}
