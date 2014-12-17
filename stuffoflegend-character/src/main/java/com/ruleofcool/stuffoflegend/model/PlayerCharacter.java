package com.ruleofcool.stuffoflegend.model;

public class PlayerCharacter {

	private static final int[] progressions = new int[] {0, 1, 2, 1};

	private int[] abilityScores;
	private Track[] tracks;
	private CharacterClass clazz;
	private String name;
	private int level;

	public PlayerCharacter() {
		abilityScores = new int[6];
		tracks = new Track[4];
	}

	public int getAbility(int index) {
		return abilityScores[index];
	}

	public void setAbility(Ability ability, int value) {
		abilityScores[ability.index] = value;
	}

	public int getKOM() {
		return 0;
	}

	public int getKDM() {
		return 0;
	}

	public Track[] getTracks() {
		return tracks;
	}

	public void setTracks(Track[] tracks) {
		this.tracks = tracks;
	}

	public CharacterClass getClazz() {
		return clazz;
	}

	public void setClazz(CharacterClass clazz) {
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
