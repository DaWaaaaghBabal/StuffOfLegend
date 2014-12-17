package com.dwb.stuffoflegend.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.dwb.stuffoflegend.factories.ClassFactory;

public class ClassManager {
	private Map<String, CharacterClass> classes;

	public ClassManager(TrackManager trackManager) {
		classes = new HashMap<>();
		initialize(trackManager);
	}

	private void initialize(TrackManager trackManager) {
		SAXBuilder jdomBuilder = new SAXBuilder();
		ClassFactory factory = ClassFactory.getInstance();
		try {
			InputStream in = ClassManager.class.getResource("/classes.xml")
					.openStream();

			Document jdomDocument = jdomBuilder.build(in);
			Element trackRoot = jdomDocument.getRootElement();

			for (Element classElement : trackRoot.getChildren()) {
				CharacterClass clazz = factory.modelFromElement(classElement);
				Track[] tracks = new Track[3];
				for (Element trackElement : classElement.getChildren()) {
					String trackId = trackElement.getAttributeValue("id");
					Track track = trackManager.getTrack(trackId);
					track.setLocked(Boolean.parseBoolean(trackElement
							.getAttributeValue("locked")));
					int progression = progressionFromName(trackElement
							.getAttributeValue("progression"));
					tracks[progression] = track;
				}
				clazz.setTracks(tracks);
				classes.put(classElement.getAttributeValue("id"), clazz);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	private int progressionFromName(String name) {
		switch (name) {
		case "slow":
			return 2;
		case "medium":
			return 1;
		case "fast":
			return 0;
		}
		return -1;
	}

	public CharacterClass getClass(String id) {
		return classes.get(id);
	}

	public Collection<CharacterClass> getClassList() {
		return classes.values();
	}
}
