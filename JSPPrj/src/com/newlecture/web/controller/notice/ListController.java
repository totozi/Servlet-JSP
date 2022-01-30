package com.newlecture.web.controller.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class ListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = 1;
		String field= "title";
		String query= "";
		String pageTemp = request.getParameter("p");
		String fieldTemp = request.getParameter("f");
		String queryTemp = request.getParameter("q");
		if(fieldTemp != null && !fieldTemp.equals(""))
			field = fieldTemp;
		if(queryTemp != null && !queryTemp.equals(""))
			query = queryTemp;
		if(pageTemp != null && !pageTemp.equals(""))
			page = Integer.parseInt(pageTemp);
		
		NoticeService service = new NoticeService();
		List<NoticeView> noticeList = service.getNoticeViewList(field,query,page);
		int count = service.getNoticeCount(field,query);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);

	
	}
}
