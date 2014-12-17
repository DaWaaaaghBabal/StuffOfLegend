package com.dwb.stuffoflegend.factories;

import org.jdom2.Element;

import com.dwb.stuffoflegend.model.Circle;
import com.dwb.stuffoflegend.model.Track;

public class TrackFactory extends ModelFactory<Track> {

	@Override
	public Track modelFromElement(Element element) {
		Track track = new Track();
		track.setName(element.getAttributeValue("name"));
		track.setKom(element.getAttributeValue("kom"));
		track.setKdm(element.getAttributeValue("kdm"));
		track.setTam(element.getAttributeValue("tam"));
		for (Element child : element.getChildren()) {
			Circle circle = CircleFactory.getInstance().modelFromElement(child);
			circle.setTrack(track);
			track.addCircle(circle);
		}
		return track;
	}

	private static TrackFactory instance;

	public static TrackFactory getInstance() {
		if (instance == null) {
			instance = new TrackFactory();
		}
		return instance;
	}

}
