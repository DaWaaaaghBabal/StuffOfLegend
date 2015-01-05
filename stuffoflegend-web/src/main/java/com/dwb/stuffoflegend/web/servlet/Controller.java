package com.dwb.stuffoflegend.web.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Controller {

	private HttpServlet	servlet;

	protected RequestDispatcher getRequestDispatcher(String name) {
		return getServlet().getServletContext().getRequestDispatcher(name);
	}

	private String	urlPattern;

	protected Logger getLogger() {
		return LogManager.getLogger(getClass());
	}

	public abstract void dispatchGet(HttpServletRequest request,
			HttpServletResponse response);

	public abstract void dispatchPost(HttpServletRequest request,
			HttpServletResponse response);

	protected String extractURI(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return requestURI.substring(urlPattern.length());
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	public HttpServlet getServlet() {
		return servlet;
	}

	public void setServlet(HttpServlet servlet) {
		this.servlet = servlet;
	}

}
