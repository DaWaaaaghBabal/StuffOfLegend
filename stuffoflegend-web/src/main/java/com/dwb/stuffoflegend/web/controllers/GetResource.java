package com.dwb.stuffoflegend.web.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dwb.stuffoflegend.web.servlet.Controller;
import com.dwb.stuffoflegend.web.servlet.UrlMapping;

@UrlMapping(pattern = "resources")
public class GetResource extends Controller {

	@Override
	public void dispatchGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String uri = "/remote" + extractURI(request);
			sendResourceToResponse(response, uri);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void sendResourceToResponse(HttpServletResponse response,
			String uri) throws IOException {
		InputStream input = getClass().getResourceAsStream(uri);
		OutputStream output = response.getOutputStream();
		
		byte[] buffer = new byte[1024];
		int readBytes;
		while((readBytes = input.read(buffer)) > 0) {
			output.write(buffer, 0, readBytes);
		}
	}

	@Override
	public void dispatchPost(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Controller GetResource handles POST request " + extractURI(request) + ": nothing to do.");
	}
	

}
