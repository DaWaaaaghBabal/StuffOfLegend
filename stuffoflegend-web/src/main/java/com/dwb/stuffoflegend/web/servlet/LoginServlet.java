package com.dwb.stuffoflegend.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dwb.stuffoflegend.data.User;
import com.dwb.stuffoflegend.database.core.UserDatabaseInteractor;
import com.dwb.stuffoflegend.database.mysql.MysqlInteractorFactory;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/loginForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MysqlInteractorFactory<UserDatabaseInteractor> factory = new MysqlInteractorFactory<UserDatabaseInteractor>(UserDatabaseInteractor.class);
		try {
			UserDatabaseInteractor interactor = factory.initInteractor();
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			User user = interactor.login(login, password);
			String redirect = null;
			if(user != null) {
				redirect = "loginSuccess";
				request.setAttribute("user", user);
			} else {
				redirect = "loginFailure";
			}
			getServletContext().getRequestDispatcher("/WEB-INF/" + redirect + ".jsp").forward(request, response);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
