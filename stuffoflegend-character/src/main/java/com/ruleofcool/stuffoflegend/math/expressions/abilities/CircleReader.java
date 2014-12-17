package com.ruleofcool.stuffoflegend.math.expressions.abilities;

import com.ruleofcool.stuffoflegend.math.Expression;
import com.ruleofcool.stuffoflegend.math.MathParser;
import com.ruleofcool.stuffoflegend.model.PlayerCharacter;
import com.ruleofcool.stuffoflegend.model.Track;

public class CircleReader extends Expression {

	@Override
	public int eval() {
		PlayerCharacter character = MathParser.getInstance().getContext()
				.getCharacter();
		int level = character.getLevel();
		Track track = MathParser.getInstance().getContext().getCurrentTrack();
		Track[] tracks = character.getTracks();
		int progressionOffset = 0;
		if (track == tracks[3])
			progressionOffset = 1;
		else
			for (int i = 0; i < 3; i++) {
				if (track == tracks[i])
					progressionOffset = i;
			}
		return (level + 3 - progressionOffset) / 3;
	}

}
