package com.dwb.stuffoflegend.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Track {

	private String name;
	private String kom;
	private String kdm;
	private String tam;

	private Map<Integer, Set<Circle>> circles;
	private boolean locked;

	public Track() {
		circles = new HashMap<>();
		for (int i = 1; i <= 7; i++) {
			circles.put(i, new HashSet<Circle>());
		}
	}

	public void addCircle(Circle c) {
		circles.get(c.getNumber()).add(c);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKom() {
		return kom;
	}

	public void setKom(String kom) {
		this.kom = kom;
	}

	public String getKdm() {
		return kdm;
	}

	public void setKdm(String kdm) {
		this.kdm = kdm;
	}

	public String getTam() {
		return tam;
	}

	public void setTam(String tam) {
		this.tam = tam;
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Set<Circle> getCircles(int n) {
		return circles.get(n);
	}

}
