package com.dwb.stuffoflegend.data;

public class Progression {

	private String name;
	private int offset;
	public Progression(String name, int offset) {
		setName(name);
		setOffset(offset);
	}

	public int getCircle(int level) {
		return (level + 3 - offset) / 3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
