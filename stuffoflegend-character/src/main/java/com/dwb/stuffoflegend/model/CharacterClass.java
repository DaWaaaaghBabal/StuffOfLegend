package com.dwb.stuffoflegend.model;

public class CharacterClass {

	private int kom, kdm, hp, skills;
	private String name;
	private Track[] tracks;
	private String id;
	
	
	public Track[] getTracks() {
		return tracks;
	}

	public void setTracks(Track[] tracks) {
		this.tracks = tracks;
	}

	public int getKom() {
		return kom;
	}

	public void setKom(int kom) {
		this.kom = kom;
	}

	public int getKdm() {
		return kdm;
	}

	public void setKdm(int kdm) {
		this.kdm = kdm;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSkills() {
		return skills;
	}

	public void setSkills(int skills) {
		this.skills = skills;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
