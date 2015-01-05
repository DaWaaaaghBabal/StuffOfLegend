package com.dwb.stuffoflegend.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dwb.stuffoflegend.database.mysql.MysqlInteractorProvider;

/**
 * Servlet for the application. Uses the URL pattern to dispatch between
 * Controllers that execute the actual work.
 */
public class EntryPointServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	private Logger				logger				= LogManager
															.getLogger(getClass());
	private static String		rootURI				= "/stuffoflegend-web/";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		getControllerForRequest(request).dispatchGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		getControllerForRequest(request).dispatchPost(request, response);
	}

	private Controller getControllerForRequest(HttpServletRequest request) {
		String urlPattern = request.getRequestURI()
				.substring("/stuffoflegend-web/".length()).split("/")[0];
		Controller controller = new ControllerManager()
				.getController(urlPattern);
		controller.setServlet(this);
		HttpSession session = request.getSession();
		session.setAttribute("provider", new MysqlInteractorProvider());
		controller.setUrlPattern(rootURI + urlPattern);
		logger.debug("Handling request for: " + urlPattern
				+ " with controller: " + controller.getClass().getSimpleName());
		return controller;
	}

}
