package com.dwb.stuffoflegend.database.core;

import static com.dwb.stuffoflegend.database.core.QueryKey.CREATE_ABILITY;
import static com.dwb.stuffoflegend.database.core.QueryKey.CREATE_CIRCLE_CHOICE;
import static com.dwb.stuffoflegend.database.core.QueryKey.CREATE_TRACK;
import static com.dwb.stuffoflegend.database.core.QueryKey.GET_TRACKS;
import static com.dwb.stuffoflegend.database.core.QueryKey.GET_TRACKS_FOR_CLASS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dwb.stuffoflegend.data.Ability;
import com.dwb.stuffoflegend.data.CharacterClass;
import com.dwb.stuffoflegend.data.Circle;
import com.dwb.stuffoflegend.data.CircleChoice;
import com.dwb.stuffoflegend.data.Progression;
import com.dwb.stuffoflegend.data.Track;

public class TrackDatabaseInteractor extends DatabaseInteractor {

	private final Map<Integer, Track>	tracks	= new HashMap<>();

	/**
	 * Gets all tracks from the database. In the Map, each entry's key is equal
	 * to the track ID.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Map<Integer, Track> getTracks() throws SQLException {
		return getTracks(true);
	}

	/**
	 * Gets all tracks from the database. In the Map, each entry's key is equal
	 * to the track ID.
	 * 
	 * @param close
	 *            true (default) if the connection should be closed at the end
	 *            of the operation.
	 * @return
	 * @throws SQLException
	 */
	public Map<Integer, Track> getTracks(boolean close) throws SQLException {
		if (tracks.isEmpty()) {
			Map<String, String> parameters = new HashMap<>();
			ResultSet resultSet = executeQuery(GET_TRACKS, parameters);
			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Track track = new Track(name, id);
				tracks.put(id, track);
			}
			if (close)
				closeConnection();
		}
		return tracks;
	}

	public Track getTrackById(int id) throws SQLException {
		return getTrackById(id, true);
	}

	public Track getTrackById(int id, boolean close) throws SQLException {
		return getTracks(close).get(id);
	}

	public Map<Progression, Set<Track>> getTrackChoices(CharacterClass clazz)
			throws SQLException {
		return getTrackChoices(clazz, true);
	}

	public Map<Progression, Set<Track>> getTrackChoices(CharacterClass clazz,
			boolean close) throws SQLException {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("class", "" + clazz.getId());
		ResultSet resultSet = executeQuery(GET_TRACKS_FOR_CLASS, parameters);
		Map<Progression, Set<Track>> tracks = null;
		ProgressionDatabaseInteractor progInteractor = InteractorProvider
				.getInstance().provideProgressionInteractor();
		tracks = populateTrackOptions(resultSet, progInteractor);
		if (close)
			closeConnection();
		return tracks;
	}

	private Map<Progression, Set<Track>> populateTrackOptions(
			ResultSet resultSet, ProgressionDatabaseInteractor progInteractor)
			throws SQLException {
		Map<Progression, Set<Track>> tracks = new HashMap<>();
		while (resultSet.next()) {
			int trackId = resultSet.getInt("track");
			int progressionId = resultSet.getInt("progression");
			Track track = getTrackById(trackId, false);
			Progression progression = progInteractor
					.getProgressionById(progressionId);
			if (tracks.get(progression) == null) {
				tracks.put(progression, new HashSet<Track>());
			}
			tracks.get(progression).add(track);
		}
		closeConnection();
		return tracks;
	}

	public void createTrack(Track track) throws SQLException {
		Map<String, String> parameters = new HashMap<>();
		if (executeUpdate(CREATE_TRACK, parameters) > 0) {
			insertCircles(track);
		}
	}

	private void insertCircles(Track track) throws SQLException {
		Map<String, String> parameters = new HashMap<>();
		int count = 1;
		int trackId = lastInsertedId();
		for (Circle circle : track.getCircles()) {
			parameters.put("track", "" + trackId);
			parameters.put("number", "" + count);
			count++;
			parameters.put("type", "" + circle.getType().getId());
			if (executeUpdate(QueryKey.CREATE_CIRCLE, parameters) > 0) {
				insertCircleChoices(circle);
			}

		}
	}

	private void insertCircleChoices(Circle circle) throws SQLException {
		Map<String, String> parameters = new HashMap<>();
		int circleId = lastInsertedId();
		parameters.clear();
		parameters.put("circle", "" + circleId);
		for (CircleChoice choice : circle.getChoices().values()) {
			if (executeUpdate(CREATE_CIRCLE_CHOICE, parameters) > 0) {
				insertAbilities(choice);
			}

		}
	}

	private void insertAbilities(CircleChoice choice) throws SQLException {
		Map<String, String> parameters = new HashMap<>();
		int choiceId = lastInsertedId();
		parameters.clear();
		parameters.put("circle_choice", "" + choiceId);
		for (Ability ability : choice.getAbilities().values()) {
			parameters.put("ability_type", "" + ability.getType().getId());
			if (executeUpdate(CREATE_ABILITY, parameters) > 0) {
				translateAbility(ability);
			}
		}
	}

	private void translateAbility(Ability ability) throws SQLException {
		Map<String, String> parameters = new HashMap<>();
		int abilityId = lastInsertedId();
		parameters.put("ability", "" + abilityId);
		parameters.put("name", ability.getName());
		parameters.put("text", ability.getText());
		executeUpdate(QueryKey.TRANSLATE_ABILITY, parameters);
	}

	@Override
	public void clearCache() {
		tracks.clear();
	}

}
