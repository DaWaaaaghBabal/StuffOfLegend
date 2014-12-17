package com.ruleofcool.stuffoflegend.math;

/**
 * Basic mathematical operation used in an abstract syntax tree for mathematical
 * formulae. The whole math parsing package is NOT intended for a generic use :
 * it is designed for the math of the Legend system, e.g it uses only ints,
 * rounds all divisions down, etc. In most other contexts, this is an incomplete
 * math parsing system that may give irrelevant or false results.
 * 
 * @author Jean-Sébastien
 */
public abstract class Expression {
	protected ExpressionContext context;

	public abstract int eval();

	public ExpressionContext getContext() {
		return context;
	}

	public void setContext(ExpressionContext context) {
		this.context = context;
	}
}
