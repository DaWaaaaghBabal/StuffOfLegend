package com.ruleofcool.stuffoflegend.math;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class TestMath extends TestCase {

	private MathParser parser;

	@Override
	public void setUp() {
		parser = MathParser.getInstance();
	}

	@Test
	public void testBasicOperators() {
		String formula = "5+(4+3)*2";
		int expected = 19;
		Expression expression = parser.parseFormula(formula);
		int actual = expression.eval();
		Assert.assertEquals(expected, actual);

		formula = "(5+4)*(3+2)";
		expected = 45;
		expression = parser.parseFormula(formula);
		actual = expression.eval();
		Assert.assertEquals(expected, actual);

		formula = "((5+4)*2+3)*(3+2)";
		expected = 105;
		expression = parser.parseFormula(formula);
		actual = expression.eval();
		Assert.assertEquals(expected, actual);

		formula = "2*TEST";
		expected = 84;
		expression = parser.parseFormula(formula);
		actual = expression.eval();
		Assert.assertEquals(expected, actual);
	}

}
