package com.dwb.stuffoflegend.database.core;

public class Language {

	public static final String	PARAMETER_NAME	= "language";
	private static int	current = 1;

	public static int getCurrent() {
		return current;
	}

	public static void setCurrent(int current) {
		Language.current = current;
	}

}
