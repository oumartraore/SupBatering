<%-- 
    Document   : detail
    Created on : 14 déc. 2015, 01:27:31
    Author     : oumartraore
--%>

<%@page import="com.supbatering.entity.Supobject"%>
<jsp:include page="header.jsp" />

<%
    Supobject supobject = (Supobject)request.getAttribute("supobjects");
%>

<section>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h1 class="text-center"> More Detail </h1>
                
                <h1> Name : <%= supobject.getName() %> </h1>
                
                <h1> Description : <%= supobject.getDescription() %> </h1>
                
                <h1> Price : <%= supobject.getPrice() %> </h1>
                
                <img src=<%= supobject.getFilepath() %> />
                
                <h1> Posted by  : <%= supobject.getSupuser().getUsername() %> </h1>
                
                <h1> Contact Email : <%= supobject.getSupuser().getEmail() %> </h1>
                
                <h1> Contact Code Postale : <%= supobject.getSupuser().getCodePostale() %> </h1>
                
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp" />
