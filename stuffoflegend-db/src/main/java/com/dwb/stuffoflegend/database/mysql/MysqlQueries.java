package com.dwb.stuffoflegend.database.mysql;

import static com.dwb.stuffoflegend.database.core.Queries.QueryKey.CREATE_USER;
import static com.dwb.stuffoflegend.database.core.Queries.QueryKey.LOGIN;

import com.dwb.stuffoflegend.database.core.Queries;

public class MysqlQueries extends Queries {

	public MysqlQueries() {
		super();
		queries.put(LOGIN, "select id, login, email from users where login='${login}' and password='${password}'");
		queries.put(CREATE_USER, "insert into users (id, login, email, password) values (NULL, '${login},'${email},'${password}");
	}

}
