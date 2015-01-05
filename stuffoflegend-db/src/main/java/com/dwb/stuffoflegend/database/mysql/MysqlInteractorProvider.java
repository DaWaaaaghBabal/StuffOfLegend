package com.dwb.stuffoflegend.database.mysql;

import com.dwb.stuffoflegend.database.core.DatabaseInteractor;
import com.dwb.stuffoflegend.database.core.InteractorFactory;
import com.dwb.stuffoflegend.database.core.InteractorProvider;

public class MysqlInteractorProvider extends InteractorProvider {

	protected <T extends DatabaseInteractor> InteractorFactory<T> initFactory(Class<T> clazz) {
		return new MysqlInteractorFactory<T>(clazz);
	}
}
