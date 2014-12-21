package com.dwb.stuffoflegend.database.mysql;

import com.dwb.stuffoflegend.database.core.DatabaseConnector;
import com.dwb.stuffoflegend.database.core.DatabaseInteractor;
import com.dwb.stuffoflegend.database.core.InteractorFactory;
import com.dwb.stuffoflegend.database.core.Queries;

public class MysqlInteractorFactory<T extends DatabaseInteractor>  extends InteractorFactory<T> {

	public MysqlInteractorFactory(Class<T> clazz) {
		super(clazz);
	}

	@Override
	protected DatabaseConnector initDbConnector() {
		return new MysqlDatabaseConnector();
	}

	@Override
	protected Queries initQueries() {
		return new MysqlQueries();
	}

}
