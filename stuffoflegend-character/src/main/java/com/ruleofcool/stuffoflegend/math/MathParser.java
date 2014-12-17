package com.ruleofcool.stuffoflegend.math;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.ruleofcool.stuffoflegend.math.expressions.Difference;
import com.ruleofcool.stuffoflegend.math.expressions.DualExpression;
import com.ruleofcool.stuffoflegend.math.expressions.FixedValue;
import com.ruleofcool.stuffoflegend.math.expressions.Product;
import com.ruleofcool.stuffoflegend.math.expressions.Quotient;
import com.ruleofcool.stuffoflegend.math.expressions.Sum;

public class MathParser {

	private Map<Character, Class<? extends DualExpression>> operatorClasses;
	private Map<String, Class<? extends Expression>> placeholderClasses;
	private ExpressionContext context;
	private static MathParser instance;

	public static MathParser getInstance() {
		if (instance == null)
			instance = new MathParser();
		return instance;
	}

	private MathParser() {
		operatorClasses = new HashMap<Character, Class<? extends DualExpression>>();
		operatorClasses.put('+', Sum.class);
		operatorClasses.put('-', Difference.class);
		operatorClasses.put('*', Product.class);
		operatorClasses.put('/', Quotient.class);

		placeholderClasses = new HashMap<>();
		Properties placeholders = new Properties();
		try {
			placeholders.load(getClass().getResourceAsStream("/placeholders"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (Entry<Object, Object> entry : placeholders.entrySet()) {
			try {
				@SuppressWarnings("unchecked")
				Class<? extends Expression> clazz = (Class<? extends Expression>) Class
						.forName((String) entry.getValue());
				placeholderClasses.put((String) entry.getKey(), clazz);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		setContext(new ExpressionContext());
	}

	/**
	 * Returns an Expression that can be evaluated ; it is an abstract syntax
	 * tree that organizes operators in the appropriate order.
	 * 
	 * @param formula
	 * @return
	 */
	public Expression parseFormula(String formula) {

		char[] priorities = new char[] {'+', '-', '/', '*'};
		for (char operator : priorities) {
			String[] splitResult = split(operator, formula);
			if (splitResult[1].length() > 0) {
				try {
					DualExpression expression = operatorClasses.get(operator)
							.newInstance();
					expression.setLeftTerm(parseFormula(splitResult[0]));
					expression.setRightTerm(parseFormula(splitResult[1]));
					return expression;
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		// No operators found. Either this formula was between parenthesises or
		// it is a single value;
		if (formula.charAt(0) == '(') {
			String extracted = formula.substring(1, formula.length() - 1);
			return parseFormula(extracted);
		} else
			try {
				return new FixedValue(Integer.parseInt(formula));
			} catch (NumberFormatException e) {
				return parseToken(formula);
			}
	}

	private Expression parseToken(String substring) {
		Class<? extends Expression> clazz = placeholderClasses.get(substring);
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return new FixedValue(0);
	}

	private String[] split(char operator, String formula) {
		int parenthesisCount = 0;
		for (int i = 0; i < formula.length(); i++) {
			char c = formula.charAt(i);
			if (c == '(')
				parenthesisCount++;
			else if (c == ')')
				parenthesisCount--;
			else if (c == operator && parenthesisCount == 0) {
				return new String[] {formula.substring(0, i),
						formula.substring(i + 1)};
			}
		}
		return new String[] {formula, ""};
	}

	public ExpressionContext getContext() {
		return context;
	}

	public void setContext(ExpressionContext context) {
		this.context = context;
	}

}
