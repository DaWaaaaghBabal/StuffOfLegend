package com.dwb.stuffoflegend.model;

import java.util.List;

public class StatChoice {
	private List<Stat> options;

	private Stat choice;

	public List<Stat> getOptions() {
		return options;
	}

	public void setOptions(List<Stat> options) {
		this.options = options;
	}

	public Stat getChoice() {
		return choice;
	}

	public void setChoice(Stat choice) {
		this.choice = choice;
	}
}
