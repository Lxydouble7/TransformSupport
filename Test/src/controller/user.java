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

import java.util.Random;
import java.util.*;
import java.text.SimpleDateFormat;

@WebServlet("/user")
public class user extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int i = 0;
        String fileName = null;

        Random df = new Random();
        System.setProperty("sun.jnu.encoding","utf-8");
        try {
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for(FileItem fileItem : list) {
                //fileName = fileItem.getName();
                fileName = String.valueOf(df.nextInt(100000)) + ".csv";
                long size = fileItem.getSize();
                System.out.println(fileName+":"+size+"Byte");
                InputStream inputStream = fileItem.getInputStream();
                String path1 = "C:\\Users\\Administrator\\Desktop\\TransformSupport\\raw\\" + fileName;
                OutputStream outputStream = new FileOutputStream(path1);
                int temp = 0;
                while((temp = inputStream.read())!=-1){
                    outputStream.write(temp);
                }
                outputStream.close();
                inputStream.close();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        ArrayList<String> warninglist = new ArrayList<String>();
        Process proc;
        try {
            String instruction = "python C:\\Users\\Administrator\\Desktop\\TransformSupport\\main.py " +  fileName;
            //proc = Runtime.getRuntime().exec("python C:\\Users\\citish02\\PycharmProjects\\pythonProject\\main.py ");
            proc = Runtime.getRuntime().exec(instruction);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                warninglist.add(line);
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
            proc.destroy();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        req.setAttribute("warninglist",warninglist);
        session.setAttribute("filename",fileName);
        req.getRequestDispatcher("result.jsp").forward(req,resp);
    }
}
