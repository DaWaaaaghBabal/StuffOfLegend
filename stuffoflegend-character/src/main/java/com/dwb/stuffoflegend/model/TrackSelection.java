package com.dwb.stuffoflegend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * This is used to represent a Track selection, both as an option
 * given by the rules and as a choice made by the player.
 * Each class assigns a number of default Tracks to progressions ; these Tracks can later be swapped.
 * This class doesn't give access to a Track's Circles, it's used only for the selection of Tracks.
 */
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TrackSelection {

	@XmlAttribute
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
