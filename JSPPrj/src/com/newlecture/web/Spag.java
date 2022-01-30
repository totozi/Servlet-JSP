package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;
    	String numStr = request.getParameter("n");
    	if(numStr != null && !numStr.equals(""))
    		num = Integer.parseInt(numStr);
    	String result;
    	num = 13;
    	if(num%2 != 0){
    		result = "홀수";
    	} else {
    		result = "짝수";
    	}
    	//redirect - 작업한 내용과 상관없이
    	//forward - 현재 작업한 내용 이어가도록
    	request.setAttribute("result", result);
    	
    	String[] names = {"toto1", "toto2"};
    	request.setAttribute("names", names);
    	Map<String, Object> notice = new HashMap();
    	notice.put("id",1);
    	notice.put("title","java study");
    	request.setAttribute("notice", notice);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
    	dispatcher.forward(request, response);
    	
	}

}
