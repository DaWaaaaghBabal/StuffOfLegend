package com.dwb.stuffoflegend.data;

import java.util.ArrayList;
import java.util.List;

public class Track {

	public Track(String name, int id) {
		super();
		setName(name);
		setId(id);
		circles = new ArrayList<>();
	}
	private List<Circle> circles;
	private String name;
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Circle> getCircles() {
		return circles;
	}

	public void setCircles(List<Circle> circles) {
		this.circles = circles;
	}
	
}
