package com.dwb.stuffoflegend.web.controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dwb.stuffoflegend.data.Ability;
import com.dwb.stuffoflegend.data.AbilityType;
import com.dwb.stuffoflegend.data.Circle;
import com.dwb.stuffoflegend.data.Track;
import com.dwb.stuffoflegend.database.core.AbilityDatabaseInteractor;
import com.dwb.stuffoflegend.database.core.InteractorProvider;
import com.dwb.stuffoflegend.web.servlet.Controller;
import com.dwb.stuffoflegend.web.servlet.UrlMapping;

@UrlMapping(pattern = "createTrack")
public class TrackCreation extends Controller {

	@Override
	public void dispatchGet(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			getRequestDispatcher("/WEB-INF/trackCreationForm.jsp").forward(
					request, response);
		} catch (ServletException | IOException e) {
			getLogger().error(
					"Exception while forwarding to track creation JSP:");
			getLogger().error(e);
		}
	}

	@Override
	public void dispatchPost(HttpServletRequest request,
			HttpServletResponse response) {
		/*
		 * The track creation form is dynamic ; the request contains a variable
		 * number of parameters, with a specific name pattern. We construct the
		 * tree of circles, choices and abilities by splitting the parameter
		 * names on the dot, which gives us: splitString[0] = "circle"
		 * splitString[1] = circle number splitString[2] = circle choice number
		 * splitString[3] = ability number splitString[4] = "name", "text" or
		 * "type". The other type of parameters are built on the same
		 * dot-separated principlee : splitString[0] = "circle" splitString[1] =
		 * circle number splitString[2] = "name" or "type"
		 */
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameterMap = request.getParameterMap();
		AbilityDatabaseInteractor interactor = ((InteractorProvider) request
				.getSession().getAttribute("provider"))
				.provideAbilityInteractor();
		List<Circle> circles = initCirclesList();
		// Maps to store temporary results

		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			/*
			 * In theory, the value is a String array, according to the servlet
			 * request API. In our case, it's a single String.
			 */
			String paramValue = entry.getValue()[0];
			String paramName = entry.getKey();
			String[] splitName = paramName.split("\\.");
			Track track = new Track("", 0);
			if (splitName.length > 1) {
				int circleId = Integer.parseInt(splitName[1]);
				Circle circle = circles.get(circleId);
				if (splitName.length == 3) {
					setCircleAttribute(interactor, circle, paramValue,
							splitName);
				} else if (splitName.length == 5) {
					setAbilityAttributes(interactor, circle, paramValue, splitName);
				}
			} else {
				track.setName(paramValue);
			}
		}

	}

	private void setAbilityAttributes(AbilityDatabaseInteractor interactor,
			Circle circle, String paramValue, String[] splitName) {
		int choiceId = Integer.parseInt(splitName[2]);
		int abilityId = Integer.parseInt(splitName[3]);
		Ability ability = circle.getChoice(choiceId).getAbility(abilityId);
		if ("name".equals(splitName[2])) {
			ability.setName(paramValue);
		} else if ("type".equals(splitName[2])) {
			int typeId = Integer.parseInt(paramValue);
			AbilityType abilityType = interactor.getAbilityType(typeId);
			ability.setType(abilityType);
		} else if ("text".equals(splitName[2])) {
			ability.setText(paramValue);
		}
	}

	private void setCircleAttribute(AbilityDatabaseInteractor interactor,
			Circle circle, String paramValue, String[] splitName) {
		if ("name".equals(splitName[2])) {
			circle.setName(paramValue);
		} else if ("type".equals(splitName[2])) {
			int typeId = Integer.parseInt(paramValue);
			AbilityType abilityType = interactor.getAbilityType(typeId);
			circle.setType(abilityType);
		}
	}

	private List<Circle> initCirclesList() {
		List<Circle> list = new LinkedList<>();
		for (int i = 0; i < 7; i++) {
			list.add(new Circle());
		}
		return list;
	}

}
