package com.ruleofcool.stuffoflegend.factories;

import org.jdom2.Element;

import com.ruleofcool.stuffoflegend.model.Ability;
import com.ruleofcool.stuffoflegend.model.CharacterClass;

public class ClassFactory extends ModelFactory<CharacterClass> {

	@Override
	public CharacterClass modelFromElement(Element element) {
		CharacterClass clazz = new CharacterClass();
		clazz.setName(element.getAttributeValue("name"));
		clazz.setId(element.getAttributeValue("id"));
		clazz.setHp(Integer.parseInt(element.getAttributeValue("hp")));
		clazz.setSkills(Integer.parseInt(element.getAttributeValue("skills")));
		clazz.setKom(Ability.fromString(element
				.getAttributeValue("kom")).index);
		clazz.setKdm(Ability.fromString(element
				.getAttributeValue("kdm")).index);
		return clazz;
	}

	private static ClassFactory instance;

	public static ClassFactory getInstance() {
		if (instance == null) {
			instance = new ClassFactory();
		}
		return instance;
	}

}
