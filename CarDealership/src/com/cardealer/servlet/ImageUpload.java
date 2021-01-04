package com.cardealer.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.cardealer.entity.VehicleInputFields;
/** Code for this section adapted from: Java File Upload Example with Servlet 3.0 API
	https://www.codejava.net/java-ee/servlet/java-file-upload-example-with-servlet-30-api
    Written by  Nam Ha Minh
    Last Updated on 27 June 2019
    */
@WebServlet("/ImageUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 256, // 0.25 megabyte
		maxFileSize = 1024 * 1024 * 4, // 4 megabytes
		maxRequestSize = 1024 * 1024 * 20) // 20 megabytes
public class ImageUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/** Name of the directory where uploaded files will be saved, relative to the web application directory. */
	private String imageFolder = "images";
	private String backupFolder = "C:\\Coding\\Java\\Workspace Java\\CarDealership\\WebContent\\images";
	private String[] typesAllowed = { "image/jpg", "image/jpeg", "image/gif", "image/png" };

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		// gets absolute path of the web application
		String absolutePath = request.getServletContext().getRealPath("");
		// Add appPath to the image folder to form the path to save the image
		String savePath = absolutePath + imageFolder;
		String fileName = "";
		boolean success = false;
		if (request.getParts().isEmpty()) {
			success = false;
		} else {
			writeLoop: for (Part tempPart : request.getParts()) {
				String contentType = tempPart.getContentType();
				for (String type : typesAllowed) {
					if (contentType.equalsIgnoreCase(type.trim())) {
						success = true;
					}
				}
				if (success == false) {
					break writeLoop;
				}
				fileName = extractFileName(tempPart);
				// refines the fileName in case it is an absolute path
				fileName = new File(fileName).getName();
				tempPart.write(savePath + File.separator + fileName);
				tempPart.write(backupFolder + File.separator + fileName);
			}
		}
		@SuppressWarnings("unchecked")
		VehicleInputFields fields = (VehicleInputFields)session.getAttribute("vehicleFields");
		if (fields == null) {
			fields = new VehicleInputFields();
		}
System.out.println("Uploading...Was it successful? " + success);
		if (success) {
			String pictureLink = imageFolder + File.separator + fileName;
			session.setAttribute("pictureLink", pictureLink);
			fields.flagPictureLink(true, pictureLink);
			response.sendRedirect("addVehicle.jsp");
			return;
		} else {

			fields.flagPictureLink(false, "Upload failed");
			response.sendRedirect("addVehicle.jsp");
			return;
		}
	}

	/** Extracts file name from HTTP header content-disposition */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
