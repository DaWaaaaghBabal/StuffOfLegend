package com.ruleofcool.stuffoflegend.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.ruleofcool.stuffoflegend.factories.TrackFactory;

public class TrackManager {

	private Map<String, Track> tracks;

	public TrackManager() {
		tracks = new HashMap<>();
		initialize();
	}

	private void initialize() {
		SAXBuilder jdomBuilder = new SAXBuilder();
		try {
			InputStream in = TrackManager.class.getResource("/tracks.xml")
					.openStream();

			Document jdomDocument = jdomBuilder.build(in);
			Element trackRoot = jdomDocument.getRootElement();
			for (Element trackElement : trackRoot.getChildren()) {
				Track track = TrackFactory.getInstance().modelFromElement(
						trackElement);
				tracks.put(trackElement.getAttributeValue("id"), track);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	public Track getTrack(String id) {
		return tracks.get(id);
	}

	public Map<String, Track> getTracks() {
		return tracks;
	}
}
