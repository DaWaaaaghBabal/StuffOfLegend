package com.dwb.stuffoflegend.data;

public class CharacterClass {

	private int		hp;
	private int		skills;
	private String	name;
	private int		id;
	private float	bab;

	public CharacterClass(int id, String name, int hp, int skills, float bab) {
		setId(id);
		setName(name);
		setHp(hp);
		setSkills(skills);
		setBab(bab);
	}

	// ////Accessors
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getBab() {
		return bab;
	}

	public void setBab(float bab) {
		this.bab = bab;
	}

}
