package com.ruleofcool.stuffoflegend.factories;

import org.jdom2.Element;

public abstract class ModelFactory<T> {
	
	public abstract T modelFromElement(Element element);

}
