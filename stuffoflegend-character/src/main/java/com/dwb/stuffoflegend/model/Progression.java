package com.dwb.stuffoflegend.model;

public enum Progression {

	FAST(0), MEDIUM(1), SLOW(2);

	private Progression(int offset) {
		this.offset = offset;
	}

	private int offset;

	public int getCircle(int level) {
		return (level + 3 - offset) / 3;
	}
}
