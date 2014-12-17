package com.ruleofcool.stuffoflegend.math.expressions;

import com.ruleofcool.stuffoflegend.math.Expression;

public abstract class DualExpression extends Expression {

	protected Expression leftTerm, rightTerm;

	public Expression getLeftTerm() {
		return leftTerm;
	}
	public void setLeftTerm(Expression leftTerm) {
		this.leftTerm = leftTerm;
	}
	public Expression getRightTerm() {
		return rightTerm;
	}
	public void setRightTerm(Expression rightTerm) {
		this.rightTerm = rightTerm;
	}

}
