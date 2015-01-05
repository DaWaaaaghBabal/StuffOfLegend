package com.dwb.stuffoflegend.database.core;

import static com.dwb.stuffoflegend.database.core.QueryKey.LAST_INSERTED_ID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import com.dwb.stuffoflegend.database.core.QueryKey;

public abstract class DatabaseInteractor {

	private InteractorProvider	interactorProvider;

	private DatabaseConnector	dbConnector;
	private Queries				queries;

	public void setDatabaseConnector(DatabaseConnector dbConnector) {
		this.dbConnector = dbConnector;
	}

	public void setQueries(Queries queries) {
		this.queries = queries;
	}

	public InteractorProvider getInteractorProvider() {
		return interactorProvider;
	}

	public void setInteractorProvider(InteractorProvider interactorProvider) {
		this.interactorProvider = interactorProvider;
	}

	public abstract void clearCache();

	protected ResultSet executeQuery(QueryKey key,
			Map<String, String> parameters) {
		String query = queries.getFormattedQuery(key, parameters);
		dbConnector.openConnection();
		ResultSet resultSet = dbConnector.executeQuery(query);
		return resultSet;
	}

	protected int executeUpdate(QueryKey key, Map<String, String> parameters) {
		String query = queries.getFormattedQuery(key, parameters);
		dbConnector.openConnection();
		return dbConnector.executeUpdate(query);
	}

	protected void closeConnection() {
		dbConnector.closeConnection();
	}

	protected int lastInsertedId()
			throws SQLException {
		ResultSet resultSet  = executeQuery(LAST_INSERTED_ID, new HashMap<String, String>());
		resultSet.next();
		return resultSet.getInt("id");
	}

	protected void trace(String message) {
		LogManager.getLogger(getClass()).trace(message);
	}
	protected void debug(String message) {
		LogManager.getLogger(getClass()).debug(message);
	}
	protected void info(String message) {
		LogManager.getLogger(getClass()).info(message);
	}
	protected void warn(String message) {
		LogManager.getLogger(getClass()).warn(message);
	}
	protected void error(String message) {
		LogManager.getLogger(getClass()).error(message);
	}
}
