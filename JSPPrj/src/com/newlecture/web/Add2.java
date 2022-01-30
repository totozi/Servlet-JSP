package com.newlecture.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String[] arrOfnums = request.getParameterValues("num");
		
		int result=0;
		
		for(int i=0; i<arrOfnums.length;i++) {
			int num = Integer.parseInt(arrOfnums[i]);
			result +=num;
			//반복문안에서 선언은 반복되지 않는다.
		}
		
		response.getWriter().printf("result is %d\n", result);
		
	}

}
