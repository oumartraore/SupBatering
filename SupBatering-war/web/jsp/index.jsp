<%-- 
    Document   : index
    Created on : 11 déc. 2015, 16:17:25
    Author     : oumartraore
--%>

<%@page import="com.supbatering.entity.Supobject"%>
<%@page import="java.util.List"%>
<%@page import="com.supbatering.session.NewSessionBean"%>
<%
    NewSessionBean newSessionBean = new NewSessionBean();
    int countP = newSessionBean.getSessionCount();  
%>

<jsp:include page="header.jsp" />

<section>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                <h1 class="text-center"> Welcome To Supbatering Web Site </h1>
            </div>
       </div>
            
        <div class="row">
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-left">
                <strong> Connected : </strong>  <%= countP %> <br />
            </div>  
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-right">
                <strong> Total Object : </strong>  <%= request.getAttribute("countobjects") %> 
            </div>
        </div>    
        
        <br />
       
        <div class="panel panel-info">
            <div class="panel-heading"><h3>Description</h3></div>
            <div class="panel-body">
              Create your own bartering.
            </div>
        </div>
            
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-justify">
                
                <form class="form-horizontal" method="POST" action="Start">
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon1">SEARCH</span>
                        <input type="search" class="form-control" placeholder="Object : name or price or description or type " aria-describedby="basic-addon1" name="searchField" required>
                        <span class="input-group-btn">
                            <button class="btn btn-danger" type="submit">Find</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
        
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-justify">
                <h4 class="text-center"> 10 Last Objects </h4>
                
                <div class="table-responsive">
                    <table class="table table-hover">
                        <tr>
                            <th> Name </th>
                            <th> Price </th> 
                            <th> Description </th>
                            <th> Detail </th>
                        </tr>
                        <%
                            List<Supobject> supobjects = (List<Supobject>) request.getAttribute("objects"); 
                            for (Supobject supobject: supobjects) {   
                            %>
                            <tr>
                                <td><%= supobject.getName() %></td>
                                <td><%= supobject.getPrice() %></td>
                                <td><%= supobject.getDescription() %></td>
                                <td> 
                                    <form method="POST" action="Detail">
                                       <input type="hidden" name="param" value=<%= supobject.getId() %> /> 
                                       <input type="submit" value="More Detail" name="upload" class="btn btn-default"/>
                                    </form>
                                </td>
                             </tr>
                        <%}%>
                    </table>
                </div>
                
            </div>
        </div>
        
    </div>
</section>
        
<jsp:include page="footer.jsp" />
