package com.dwb.stuffoflegend.math;

import com.dwb.stuffoflegend.model.PlayerCharacter;
import com.dwb.stuffoflegend.model.Track;

public class ExpressionContext {

	private PlayerCharacter character;
	private Track currentTrack;

	public PlayerCharacter getCharacter() {
		return character;
	}

	public void setCharacter(PlayerCharacter character) {
		this.character = character;
	}

	public Track getCurrentTrack() {
		return currentTrack;
	}

	public void setCurrentTrack(Track currentTrack) {
		this.currentTrack = currentTrack;
	}
}
