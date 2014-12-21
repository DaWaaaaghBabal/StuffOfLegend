package com.dwb.stuffoflegend.database.core;

public abstract class InteractorFactory <T extends DatabaseInteractor> {
	
	private Class<T>	clazz;
	public InteractorFactory(Class<T> clazz) {
		this.clazz = clazz;
	}
	public T initInteractor() throws InstantiationException, IllegalAccessException {
		T interactor = clazz.newInstance();
		populate(interactor);
		return interactor;
	}
	private void populate(T interactor) {
		interactor.setDatabaseConnector(initDbConnector());
		interactor.setQueries(initQueries());
	}

	protected abstract DatabaseConnector initDbConnector() ;
	protected abstract Queries initQueries() ;
	
}
