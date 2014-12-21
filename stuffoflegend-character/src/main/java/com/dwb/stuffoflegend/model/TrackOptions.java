package com.dwb.stuffoflegend.model;

import java.util.List;
/**
 * Some character classes give alternate choices. This is used to store
 * these options.
 */
public class TrackOptions extends TrackSelection {

	public List<TrackSelection> getOptions() {
		return options;
	}

	public void setOptions(List<TrackSelection> options) {
		this.options = options;
	}

	private List<TrackSelection> options;
	
}
