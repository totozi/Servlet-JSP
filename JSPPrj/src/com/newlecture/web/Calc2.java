package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String value = request.getParameter("value");
		String operator = request.getParameter("operator");

		int intValue = 0;
		int result = 0;
		if (!value.equals(""))
			intValue = Integer.parseInt(value);

		if (operator.equals("=")) {
			// 계산
//			int x = (Integer)application.getAttribute("value");
//			int x = Integer.parseInt((String) application.getAttribute("value"));
//			int x = Integer.parseInt((String) session.getAttribute("value"));
			int x = 0;
			for (Cookie c : cookies) {
				if (c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			String operatorIn="";
			for (Cookie c : cookies) {
				if (c.getName().equals("operator")) {
					operatorIn = c.getValue();
					break;
				}
			}
			int y = intValue;
//			String operatorIn = (String)application.getAttribute("operator");
//			String operatorIn = (String) session.getAttribute("operator");
			if (operatorIn.equals("+"))
				result = x + y;
			else
				result = x - y;

			response.getWriter().printf("result is %d\n", result);
		} else {
			// 저장
//			application.setAttribute("value", value);
//			application.setAttribute("operator", operator);

//			session.setAttribute("value", value);
//			session.setAttribute("operator", operator);
			Cookie valueCookie = new Cookie("value", value);
			Cookie operatorCookie = new Cookie("operator", operator);
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(60*60);;
			operatorCookie.setPath("/calc2");
			response.addCookie(valueCookie);
			response.addCookie(operatorCookie);
			
			response.sendRedirect("calc2.html");
		}

	}

}
