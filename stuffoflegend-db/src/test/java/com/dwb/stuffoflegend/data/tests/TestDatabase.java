package com.dwb.stuffoflegend.data.tests;

import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.dwb.stuffoflegend.data.CharacterClass;
import com.dwb.stuffoflegend.data.Progression;
import com.dwb.stuffoflegend.data.Track;
import com.dwb.stuffoflegend.database.core.ClassDatabaseInteractor;
import com.dwb.stuffoflegend.database.core.InteractorProvider;
import com.dwb.stuffoflegend.database.core.TrackDatabaseInteractor;


public class TestDatabase extends TestCase {
	TrackDatabaseInteractor trackInteractor;
	ClassDatabaseInteractor classInteractor;
	@Before
	public void setUp() throws Exception {
		trackInteractor = InteractorProvider.getInstance().provideTrackInteractor();
		classInteractor = InteractorProvider.getInstance().provideClassInteractor();
	}

	@Test
	public void testTracksList() {
		System.out.println("Tracks currently in the database:");
		Map<Integer, Track> tracks;
		try {
			tracks = trackInteractor.getTracks(true);
			for(Track track : tracks.values()) {
				System.out.println(track.getId() + " : " + track.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testClassesList() {
		System.out.println("Classes currently in the database:");
		Map<Integer, CharacterClass> classes = classInteractor.getClasses();
		for(CharacterClass clazz : classes.values()) {
			System.out.println(clazz.getId() + " : " + clazz.getName());
		}
	}
	
	@Test
	public void testClassTracks() {
		System.out.println("Classes currently in the database, with the associated Tracks:");
		for(CharacterClass clazz : classInteractor.getClasses().values()) {
			System.out.println(clazz.getId() + ": " + clazz.getName());
			Map<Progression, Set<Track>> trackChoices;
			try {
				trackChoices = trackInteractor.getTrackChoices(clazz, true);
				for (Entry<Progression, Set<Track>> entry : trackChoices.entrySet()) {
					System.out.println("\t" + entry.getKey().getName() + ":");
					Set<Track> tracks = entry.getValue();
					for(Track track : tracks) {
						System.out.println("\t\t" + track.getName());
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testClassInsertion(){
		System.out.println("Inserting a new class... Before:");
		testClassesList();
		CharacterClass clazz = new CharacterClass(0, "Barbarian", 10, 5, 1.0f);
		classInteractor.createClass(clazz);
		classInteractor.clearCache();
		System.out.println("After:");
		testClassesList();
	}

}
