package com.dwb.stuffoflegend.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dwb.stuffoflegend.web.servlet.UrlMapping;

@UrlMapping(pattern="css")
public class GetThemeResource extends GetResource {

	@Override
	public void dispatchGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			//@TODO theme name should be retrieved from the user.
			String themeName = "default";
			String uri = "/themes/" + themeName  + extractURI(request);
			sendResourceToResponse(response, uri);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
