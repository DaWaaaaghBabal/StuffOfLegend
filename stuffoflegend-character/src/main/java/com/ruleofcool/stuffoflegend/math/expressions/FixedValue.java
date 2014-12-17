package com.ruleofcool.stuffoflegend.math.expressions;

import com.ruleofcool.stuffoflegend.math.Expression;

public class FixedValue extends Expression {

	private int value;

	@Override
	public int eval() {
		return value;
	}

	public FixedValue(int value) {
		super();
		this.value = value;
	}

}
