package com.dwb.stuffoflegend.model;

import java.util.Map;

public class CharacterClass {

	private StatChoice kom;
	private StatChoice kdm;
	private int hp;
	private int skills;
	private String name;
	private Map<Progression, TrackSelection> tracks;
	private String id;

	public StatChoice getKom() {
		return kom;
	}
	public void setKom(StatChoice kom) {
		this.kom = kom;
	}
	public StatChoice getKdm() {
		return kdm;
	}
	public void setKdm(StatChoice kdm) {
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
	public Map<Progression, TrackSelection> getTracks() {
		return tracks;
	}
	public void setTracks(Map<Progression, TrackSelection> tracks) {
		this.tracks = tracks;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
