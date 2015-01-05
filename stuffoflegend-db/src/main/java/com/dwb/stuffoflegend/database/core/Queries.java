package com.dwb.stuffoflegend.database.core;

import java.util.HashMap;
import java.util.Map;

public abstract class Queries extends HashMap<QueryKey, String> {
	private static final long	serialVersionUID	= 8431899735073015107L;

	public String getFormattedQuery(QueryKey key, Map<String, String> parameters) {
		if (parameters.get(Language.PARAMETER_NAME) == null) {
			parameters.put(Language.PARAMETER_NAME, "" + Language.getCurrent());
		}
		String query = get(key);
		for (Entry<String, String> entry : parameters.entrySet()) {
			query = query.replaceAll("\\$\\{" + entry.getKey() + "\\}",
					entry.getValue());
		}
		return query;
	}
}
