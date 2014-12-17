package com.ruleofcool.stuffoflegend.math;

import com.ruleofcool.stuffoflegend.model.PlayerCharacter;
import com.ruleofcool.stuffoflegend.model.Track;

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
