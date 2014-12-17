package com.ruleofcool.stuffoflegend.math.expressions;


public class Difference extends DualExpression {

	@Override
	public int eval() {
		return leftTerm.eval() - rightTerm.eval();
	}

}
