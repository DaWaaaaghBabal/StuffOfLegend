package com.dwb.stuffoflegend.model;

public enum Ability {

	STR("STR", 0), DEX("DEX", 1), CON("CON", 2), INT("INT", 3), WIS("WIS", 4), CHA(
			"CHA", 5), REF("REF", 6), FORT("FORT", 7), WILL("WILL", 8);

	public static final int SAVE_OFFSET = 7;
	public final String label;
	public final int index;

	private Ability(String label, int index) {
		this.label = label;
		this.index = index;
	}

	public static Ability fromString(String key) {
		for (Ability constant : Ability.values()) {
			if (constant.label.equals(key)) {
				return constant;
			}
		}
		return STR;
	}

	public static Ability fromIndex(int index) {
		for (Ability constant : Ability.values()) {
			if (constant.index == index) {
				return constant;
			}
		}
		return STR;
	}

}
