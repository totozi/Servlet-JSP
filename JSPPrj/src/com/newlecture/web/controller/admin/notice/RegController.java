package com.newlecture.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");

		Collection<Part> parts = request.getParts();
		StringBuilder strBuilder = new StringBuilder();
			for (Part p : parts) {
				if(!p.getName().equals("file")) continue;
				if(p.getSize()==0) continue;
				Part filePart = p;
				String fileName = filePart.getSubmittedFileName();
				strBuilder.append(fileName);
				strBuilder.append(",");
				InputStream fileInputStream = filePart.getInputStream();
				String realPath = request.getServletContext().getRealPath("/upload");
				
				File path = new File(realPath);
				if(path.exists())
					path.mkdirs();
				
				String filePath = realPath + File.separator + fileName;
				FileOutputStream fos = new FileOutputStream(filePath);
				byte[] buffer = new byte[1024];
				int size = 0;
				while ((size = fileInputStream.read(buffer)) != -1) {
					fos.write(buffer, 0, size);
				}
				strBuilder.delete(strBuilder.length()-1, strBuilder.length());
				fos.close();
				fileInputStream.close();
			}

		boolean pub = false;
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		if (isOpen != null)
			pub = true;
		notice.setPub(pub);
		notice.setWriterId("totozi");
		notice.setFiles(strBuilder.toString());

		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);

		response.sendRedirect("list");
	}
}
