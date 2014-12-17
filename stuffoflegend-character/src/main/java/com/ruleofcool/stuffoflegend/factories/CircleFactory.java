package com.ruleofcool.stuffoflegend.factories;

import org.jdom2.Element;

import com.ruleofcool.stuffoflegend.model.PowerType;
import com.ruleofcool.stuffoflegend.model.Circle;


public class CircleFactory extends ModelFactory<Circle> {

	@Override
	public Circle modelFromElement(Element element) {
		Circle circle = new Circle();
		circle.setName(element.getAttributeValue("name"));
		circle.setType(PowerType.fromString(element.getAttributeValue("type")));
		circle.setNumber(Integer.parseInt(element.getAttributeValue("n")));
		circle.setText(element.getText());
		return circle;
	}
	private static CircleFactory instance;
	public static CircleFactory getInstance() {
		if (instance==null) {
			instance = new CircleFactory();
		}
		return instance;
	}

}
