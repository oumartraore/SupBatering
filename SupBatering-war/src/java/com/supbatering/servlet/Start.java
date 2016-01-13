/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supbatering.servlet;

import com.supbatering.dao.jpa.SupobjectDAOLocal;
import com.supbatering.entity.Supobject;
import com.supbatering.session.NewSessionBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "Start", urlPatterns = {"/Start"})
public class Start extends HttpServlet {
    @EJB
    private SupobjectDAOLocal supobjectDAO;

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
        
        List<Supobject> supobjects = supobjectDAO.FindAll();
        request.setAttribute("objects", supobjects);
        
        Long countObject = supobjectDAO.Count();
        request.setAttribute("countobjects", countObject);
        
        request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
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
        
        String searchCritere = request.getParameter("searchField");
        
        List<Supobject> supobjects = supobjectDAO.FindSearch(searchCritere);
        request.setAttribute("objects", supobjects);
        
        Long countObject = supobjectDAO.Count();
        request.setAttribute("countobjects", countObject);
        
        request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
        
    }

}
