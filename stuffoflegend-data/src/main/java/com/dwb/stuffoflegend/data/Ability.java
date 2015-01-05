package com.dwb.stuffoflegend.data;

public class Ability {
	private int id;
	private String name;
	private String text;
	private AbilityType type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AbilityType getType() {
		return type;
	}
	public void setType(AbilityType type) {
		this.type = type;
	}
}
