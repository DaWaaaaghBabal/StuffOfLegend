package com.dwb.stuffoflegend.database.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Queries {

	public enum QueryKey {
		CREATE_USER, LOGIN;
	}

	protected Map<QueryKey, String> queries;
	
	public Queries() {
		queries = new HashMap<>();
	}
	
	public String getFormattedQuery (QueryKey key, Map<String, String> parameters) {
		String query = queries.get(key);
		for(Entry<String, String> entry : parameters.entrySet()) {
			query = query.replaceAll("\\$\\{"+ entry.getKey() + "\\}", entry.getValue());
		}
		return query;
	}
}
