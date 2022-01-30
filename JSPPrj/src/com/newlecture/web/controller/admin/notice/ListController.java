package com.newlecture.web.controller.admin.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] openIds = request.getParameterValues("open-id"); 
		String[] delIds = request.getParameterValues("del-id"); 
		String cmd = request.getParameter("cmd");
		String idsTmp = request.getParameter("ids");
		String[] ids = idsTmp.trim().split(" ");
		
		NoticeService service = new NoticeService();
		
		switch(cmd) {
		case "일괄공개" :
			for(String openId : openIds)
				System.out.println(openId);
			
			List<String> oids = Arrays.asList(openIds);
			List<String> cids = new ArrayList(Arrays.asList(ids));
			cids.removeAll(oids);
			System.out.println(Arrays.asList(ids));
			System.out.println(oids);
			System.out.println(cids);
			service.pubNoticeAll(oids, cids);
			break;
		case "일괄삭제" :
			int[] idsForDel = new int[delIds.length];
			for(int i = 0;i<delIds.length;i++) 
				idsForDel[i] = Integer.parseInt(delIds[i]);
			int result = service.deleteNoticeAll(idsForDel);
			break;
		}
		
		response.sendRedirect("list");
		
	}
	
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
		List<NoticeView> noticeList = service.getNoticeViewListForAdmin(field,query,page);
		int count = service.getNoticeCountForAdmin(field,query);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);

	
	}
}

