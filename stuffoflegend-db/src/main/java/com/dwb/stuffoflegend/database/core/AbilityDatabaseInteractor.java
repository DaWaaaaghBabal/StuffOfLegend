package com.dwb.stuffoflegend.database.core;

import static com.dwb.stuffoflegend.database.core.QueryKey.CREATE_ABILITY;
import static com.dwb.stuffoflegend.database.core.QueryKey.LAST_INSERTED_ID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.dwb.stuffoflegend.data.Ability;
import com.dwb.stuffoflegend.data.AbilityType;

public class AbilityDatabaseInteractor extends DatabaseInteractor {

	private Map<Integer, AbilityType> abilityTypes = new HashMap<>();
	
	public AbilityType getAbilityType(int id) {
		return getAbilityType(id, true);
	}
	public AbilityType getAbilityType(int id, boolean close) {
		return getAbilityTypes(close).get(id);
	}
	
	private Map<Integer, AbilityType> getAbilityTypes(boolean close) {
		if (abilityTypes.isEmpty()) {
			Map<String, String> parameters = new HashMap<String, String>();
			ResultSet resultSet = executeQuery(QueryKey.GET_ABILITY_TYPES, parameters);
			try {
				while (resultSet.next()) {
					AbilityType type = new AbilityType();
					int id = resultSet.getInt("id");
					type.setId(id);
					type.setLongLabel(resultSet.getString("label_long"));
					type.setShortLabel(resultSet.getString("short_long"));
					abilityTypes.put(id, type);
				}
			} catch (SQLException e) {
				error(e.getMessage());
			}
		}
		return abilityTypes;
	}

	@Override
	public void clearCache() {
		abilityTypes.clear();
	}

}
