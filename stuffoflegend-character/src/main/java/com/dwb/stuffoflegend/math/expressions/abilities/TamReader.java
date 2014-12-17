package com.dwb.stuffoflegend.math.expressions.abilities;

import com.dwb.stuffoflegend.math.Expression;
import com.dwb.stuffoflegend.math.MathParser;

public class TamReader extends Expression {

	@Override
	public int eval() {
		return MathParser.getInstance().getContext().getCharacter().getKOM();
	}

}
