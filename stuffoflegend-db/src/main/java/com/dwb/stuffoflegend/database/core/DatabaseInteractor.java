package com.dwb.stuffoflegend.database.core;

public abstract class DatabaseInteractor {

	protected DatabaseConnector	dbConnector;
	protected Queries	queries;
	public void setDatabaseConnector(DatabaseConnector dbConnector) {
		this.dbConnector = dbConnector;
	}
	public void setQueries(Queries queries) {
		this.queries = queries;
	}
}
