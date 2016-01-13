<%-- 
    Document   : header
    Created on : 9 déc. 2015, 17:09:42
    Author     : oumartraore
--%>

<%@page import="javax.ejb.EJB"%>
<%@page import="com.supbatering.session.NewSessionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String coockieValue = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("AuthenticationOumar")) {
                coockieValue = cookie.getValue();
            }
        }
    }

    NewSessionBean newSessionBean = new NewSessionBean();
    int countP = newSessionBean.getSessionCount();  
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Bootstrap 101 Template</title>

        <!-- Bootstrap -->
        <link href="ressources/css/bootstrap.min.css" rel="stylesheet">

    
  </head>
    <body>

    <header>
        <div class="container">
            <div class="row">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                      <!-- Brand and toggle get grouped for better mobile display -->
                      <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                          <span class="sr-only">Toggle navigation</span>
                          <span class="icon-bar"></span>
                          <span class="icon-bar"></span>
                          <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand btn btn-default btn-lg" href="index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> HOME</a>
                      </div>

                      <!-- Collect the nav links, forms, and other content for toggling -->
                      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                          <li>
                            <% if(coockieValue != null){
                                if(coockieValue.equals("AuthenticationOumar") ) { %>
                                <a class="btn btn-default btn-lg" href="Manage" role="button">Manage Object</a>
                            <% } }%>
                          </li>
                          
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                          <% if(coockieValue != null){
                                if(coockieValue.equals("AuthenticationOumar") ) { %>
                          <li><a class="btn btn-default btn-lg" href="Logout" role="button">Logout</a></li>
                          <li><a class="btn btn-default btn-lg" href="Edit" role="button">Edit Profil</a></li>
                            <% } }else { %>
                          <li><a class="btn btn-default btn-lg" href="Login" role="button">Login</a></li>
                          <li><a class="btn btn-default btn-lg" href="Register" role="button">Register</a></li>
                            <% } %>
                          
                        </ul>
                      </div><!-- /.navbar-collapse -->
                    </div><!-- /.container-fluid -->
                </nav>
            </div>
                
                
        </div>
    </header>
