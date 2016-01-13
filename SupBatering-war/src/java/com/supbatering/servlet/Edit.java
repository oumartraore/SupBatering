/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supbatering.servlet;

import com.supbatering.dao.jpa.SupuserDAOLocal;
import com.supbatering.entity.Supuser;
import com.supbatering.session.NewSessionBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oumartraore
 */
@WebServlet(name = "Edit", urlPatterns = {"/Edit"})
public class Edit extends HttpServlet {
    @EJB
    private SupuserDAOLocal supuserDAO;

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
        Supuser supuser = null;
        
        if( (supuser = supuserDAO.FindOne(username, password))  != null) {
            request.setAttribute("passwordE", supuser.getPassword());
            request.setAttribute("emailE", supuser.getEmail());
            request.setAttribute("codePostaleE", supuser.getCodePostale());
            
            request.getRequestDispatcher("jsp/edit.jsp").forward(request, response);
        }
        
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
        Supuser supuserOld = supuserDAO.FindOne(username, password);
        
        //Get New Value
        String email = request.getParameter("email");
        int codePostale = Integer.parseInt( request.getParameter("codePostale") );
        String passwordE = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        
        Supuser supuserNew = new Supuser(username, passwordE, email, firstname,
                lastname, codePostale);
        
        supuserDAO.Edit(supuserOld, supuserNew);
        
        // Mise à jour de la variable de cookie
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals("passwordE")) {
                cookie.setValue(passwordE);
                response.addCookie(cookie);
            }
        }
        response.sendRedirect("Start");
    }

}
