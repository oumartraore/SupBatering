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
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oumartraore
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
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
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Supuser supuser = null;
        
        if( (supuser = supuserDAO.FindOne(username, password))  != null) {
            request.getSession().setAttribute("AuthenticationOumar", "AuthenticationOumar");
            
            
            Cookie loginCookie = new Cookie("AuthenticationOumar", "AuthenticationOumar");
            //setting cookie to expiry in 1h
            loginCookie.setMaxAge(60*60);
            response.addCookie(loginCookie);
            
            // On stocke les informations de l'utilisateur dans le cookie
            Cookie usernameE = new Cookie("userConnected", supuser.getUsername());
            Cookie passwordE = new Cookie("passwordE", supuser.getPassword());
            usernameE.setMaxAge(60*60);
            response.addCookie(usernameE);
            passwordE.setMaxAge(60*60);
            response.addCookie(passwordE);
            
            response.sendRedirect("index.jsp");
        }else {
            response.sendRedirect("Login");
        }
    }

}
