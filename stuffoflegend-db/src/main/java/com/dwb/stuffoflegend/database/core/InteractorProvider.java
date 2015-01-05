package com.dwb.stuffoflegend.database.core;

import java.util.HashMap;
import java.util.Map;

public abstract class InteractorProvider {

	private static String providerClass = "com.dwb.stuffoflegend.database.mysql.MysqlInteractorProvider";
	public static InteractorProvider getInstance() {
		try {
			return(InteractorProvider) Class.forName(providerClass).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Map<Class<? extends DatabaseInteractor>, DatabaseInteractor> interactors = new HashMap<>();
	public <T extends DatabaseInteractor> DatabaseInteractor provide(Class<T> clazz) {
		if (interactors.get(clazz) == null) {
			
			InteractorFactory<T> factory = initFactory(clazz);
			try {
				T interactor = factory.initInteractor();
				interactor.setInteractorProvider(this);
				interactors.put(clazz, interactor);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return interactors.get(clazz);
	}

	public UserDatabaseInteractor provideUserInteractor() {
		return (UserDatabaseInteractor) provide(UserDatabaseInteractor.class);
	}
	public TrackDatabaseInteractor provideTrackInteractor() {
		return (TrackDatabaseInteractor) provide(TrackDatabaseInteractor.class);
	}
	public ProgressionDatabaseInteractor provideProgressionInteractor() {
		return (ProgressionDatabaseInteractor) provide(ProgressionDatabaseInteractor.class);
	}
	public ClassDatabaseInteractor provideClassInteractor() {
		return (ClassDatabaseInteractor) provide(ClassDatabaseInteractor.class);
	}
	public AbilityDatabaseInteractor provideAbilityInteractor() {
		return (AbilityDatabaseInteractor) provide(AbilityDatabaseInteractor.class);
	}
	
	protected abstract <T extends DatabaseInteractor> InteractorFactory<T> initFactory (Class<T> clazz);

}
