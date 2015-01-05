package com.dwb.stuffoflegend.data;

import java.util.HashMap;
import java.util.Map;

public class CircleChoice {

	Map<Integer, Ability> abilities;
	
	public CircleChoice() {
		abilities = new HashMap<Integer, Ability>();
	}

	public Map<Integer, Ability> getAbilities() {
		return abilities;
	}

	public Ability getAbility(int id) {
		if (abilities.get(id) == null) {
			abilities.put(id, new Ability());
		}
		return abilities.get(id);
	}
	
}
