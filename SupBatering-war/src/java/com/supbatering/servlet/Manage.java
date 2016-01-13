/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supbatering.servlet;

import com.supbatering.dao.jpa.SupobjectDAOLocal;
import com.supbatering.dao.jpa.SupuserDAOLocal;
import com.supbatering.entity.Supobject;
import com.supbatering.entity.Supuser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author oumartraore
 */
@WebServlet(name = "Manage", urlPatterns = {"/Manage"})
@MultipartConfig
public class Manage extends HttpServlet {
    @EJB
    private SupuserDAOLocal supuserDAO;
    @EJB
    private SupobjectDAOLocal supobjectDAO;
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        
        //response.setContentType("text/html;charset=UTF-8");

        // Create path components to save the file
        final String path = "/tmp";
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            
        } catch (FileNotFoundException fne) {
            final PrintWriter writer = response.getWriter();
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

            
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        } 
        
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Long price = Long.parseLong( request.getParameter("price") );
        
        //Récuperation des informations utilisateur pour le stocker
        String username = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("usernameE")) {
                    username = cookie.getValue();
                }
                if(cookie.getName().equals("passwordE")) {
                    password = cookie.getValue();
                }
            }
        }
        Supuser supuser = supuserDAO.FindOne(username, password);

        String fileNameSubmit = path + File.separator + fileName;
        
        Supobject supobject = new Supobject(name, description, price, fileNameSubmit, supuser);
        supobjectDAO.Create(supobject);
        
        
        
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String username = null;
        String password = null;
        
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("usernameE")) {
                    username = cookie.getValue();
                }
                if(cookie.getName().equals("passwordE")) {
                    password = cookie.getValue();
                }
            }
        }
        Supuser supuser = supuserDAO.FindOne(username, password);
        
        List<Supobject> supobjects = supobjectDAO.FindForSupuser(supuser);
        request.setAttribute("objects", supobjects); 
        
        request.getRequestDispatcher("jsp/manage.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        response.sendRedirect("Manage");
    }
}
