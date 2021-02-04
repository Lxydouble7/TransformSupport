package controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import java.util.*;
import java.text.SimpleDateFormat;

@WebServlet("/admin")
public class admin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int i = 0;
        try {
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for(FileItem fileItem : list) {
                String fileName = fileItem.getName();
                long size = fileItem.getSize();
                System.out.println(fileName+":"+size+"Byte");
                InputStream inputStream = fileItem.getInputStream();
                String path = req.getServletContext().getRealPath("xlsx/source/"+fileName);
                OutputStream outputStream = new FileOutputStream(path);
                int temp = 0;
                while((temp = inputStream.read())!=-1){
                    outputStream.write(temp);
                }
                outputStream.close();
                inputStream.close();
                System.out.println("上传成功");
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
