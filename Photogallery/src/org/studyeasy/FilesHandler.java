package org.studyeasy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.studyeasy.hibernate.DAO.FilesDAO;
import org.studyeasy.hibernate.entity.Files;

@WebServlet("/FilesHandler")
public class FilesHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String path="F:/Photogallery/";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		switch (action) {
		case "filesUpload":
			filesUpload(request, response);
			break;
		case "updateInformation":
			updateInformation(request,response);
			break;
		default:
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		switch (action) {
		case "listingImages":
			listingImages(request, response);
			break;	
		case "viewImage":
			viewImage(request,response);
			break;
		case "deleteImage":
			deleteImage(request,response);
			break;
		default:
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		}
	}
	
	private void deleteImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int fileId=Integer.parseInt(request.getParameter("fileId"));
		Files file=new FilesDAO().getFile(fileId);
		if(!file.equals(null)) {
		new FilesDAO().deleteFile(fileId);
		//Logic for Delete file from filesystem
		File fileOnDisc=new File(path+file.getFilename());
		if(fileOnDisc.delete()) {
			System.out.println("File Deleted");
		}
		else {
			System.out.println("file not deleted");
		}
		}
		listingImages(request, response);
	}

	private void viewImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fileId=Integer.parseInt(request.getParameter("fileId"));
		Files file=new FilesDAO().getFile(fileId);
		request.setAttribute("file",file);
		request.setAttribute("path",path);
		request.getRequestDispatcher("viewImage.jsp").forward(request, response);
	}

	private void updateInformation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int fileId=Integer.parseInt(request.getParameter("fileId"));
		String label=request.getParameter("label");
		String caption=request.getParameter("caption");
		String filename=request.getParameter("filename");
		//Files file=new Files(fileId,label,caption,filename);
		
		new FilesDAO().updateInformation(fileId,label,caption);
		listingImages(request, response);
		
	}
	private void listingImages(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Files> files=new FilesDAO().listFile();
		request.setAttribute("files", files);
		request.setAttribute("path",path);
		request.getRequestDispatcher("listFiles.jsp").forward(request, response);
		
	}

	public void filesUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
	ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
	
	try {
		List<FileItem> images=upload.parseRequest(request);
		for(FileItem image:images) {
			String name=image.getName();
			new FilesDAO().addFileDetails(new Files(name));
			File file=new File(path+name);
			if(!file.exists()) {
			image.write(file);
			System.out.println(name);
			}
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	listingImages(request, response);
	}

}
