package erpsoltemp.erpsoltempview.erptempclass;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ERPSolTempletServlet", urlPatterns = { "/erpsoltempletservlet" })
public class ERPSolTempletServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //String path = (request.getParameter("path"));
        // System.out.println(request.getParameter("filename")+"request.getParameter(filename)");
        String erpfileName = "USER_"+request.getParameter("erpsolfilename")+".png"; //.toString();
        System.out.println(erpfileName + " fileName");
        OutputStream os = response.getOutputStream();
        InputStream inputStream = null;
        //    Connection conn = null;
        try {
            File outputFile = null;
            String docPath = "";
            //if condition checking files are storing on file system or in database

            docPath = "C:\\ADFDocs\\";
            //docPath="C:\\hello\\";
            outputFile = new File(docPath + erpfileName);
            System.out.println("outputFile");
            if (!outputFile.exists()) {
               outputFile = new File(docPath + "USER_NOPIC.png");
           }
            
            System.out.println(outputFile.exists());
            inputStream = new FileInputStream(outputFile);
            System.out.println("ONE");
            BufferedInputStream in = new BufferedInputStream(inputStream);
            System.out.println("outputfile example");
            int b;
            byte[] buffer = new byte[10240];
            while ((b = in.read(buffer, 0, 10240)) != -1) {
                os.write(buffer, 0, b);
            }

        } catch (Exception e) {
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                // TODO: Add catch code
                // e.printStackTrace();
            }
        }
    }
}
