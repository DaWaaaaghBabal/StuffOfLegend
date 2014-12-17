package com.ruleofcool.stuffoflegend.math.expressions.abilities;

import com.ruleofcool.stuffoflegend.math.Expression;
import com.ruleofcool.stuffoflegend.math.MathParser;

public class KomReader extends Expression {

	@Override
	public int eval() {
		return MathParser.getInstance().getContext().getCharacter().getKOM();
	}
}
