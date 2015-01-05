package com.dwb.stuffoflegend.data;

import java.util.HashMap;
import java.util.Map;

public class Circle {

	private String				name;
	private AbilityType			type;
	private Map<Integer, CircleChoice>	choices;

	public Circle() {
		choices = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AbilityType getType() {
		return type;
	}

	public void setType(AbilityType type) {
		this.type = type;
	}

	public CircleChoice getChoice(int id) {
		if(choices.get(id) == null) {
			choices.put(id, new CircleChoice());
		}
		return choices.get(id);
	}

	public Map<Integer, CircleChoice> getChoices() {
		return choices;
	}
}
