package com.dwb.stuffoflegend.math.expressions.abilities;

import com.dwb.stuffoflegend.math.Expression;
import com.dwb.stuffoflegend.math.MathParser;

public abstract class AbilityReader extends Expression {

	private int index;

	public AbilityReader(int index) {
		super();
		this.index = index;
	}

	@Override
	public int eval() {
		return MathParser.getInstance().getContext().getCharacter()
				.getAbility(index);
	}

}
