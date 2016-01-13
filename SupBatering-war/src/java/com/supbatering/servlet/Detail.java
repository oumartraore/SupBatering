/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supbatering.servlet;

import com.supbatering.dao.jpa.SupobjectDAOLocal;
import com.supbatering.entity.Supobject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oumartraore
 */
@WebServlet(name = "Detail", urlPatterns = {"/Detail"})
public class Detail extends HttpServlet {
    @EJB
    private SupobjectDAOLocal supobjectDAO;


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
        
        Long param = Long.parseLong(request.getParameter("param"));
        
        Supobject supobject = null;
        if( (supobject = supobjectDAO.FindOne(param)) != null) {
            request.setAttribute("supobjects", supobject); 
            
            request.getRequestDispatcher("jsp/detail.jsp").forward(request, response);
        }
        
        processRequest(request, response);
    }

}
