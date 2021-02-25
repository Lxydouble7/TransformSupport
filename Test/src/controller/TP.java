package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/TP")
public class TP extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filename = "字典.xlsx";
        resp.setContentType("application/x-msdownload");
        resp.setHeader("Content-Disposition","attacment;filename="+filename);
        OutputStream download = resp.getOutputStream();
        String path2 = "C:\\Users\\Administrator\\Desktop\\TransformSupport\\source\\" + filename;
        InputStream downloadInput = new FileInputStream(path2);
        int temp = 0;
        while((temp = downloadInput.read())!= -1){
            download.write(temp);
        }
    }
}
