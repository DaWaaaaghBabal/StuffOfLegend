package com.dwb.stuffoflegend.math.expressions;

import com.dwb.stuffoflegend.math.Expression;

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
