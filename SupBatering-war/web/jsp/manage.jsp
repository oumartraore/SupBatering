<%-- 
    Document   : manage
    Created on : 11 déc. 2015, 20:01:45
    Author     : oumartraore
--%>

<%@page import="com.supbatering.entity.Supobject"%>
<%@page import="java.util.List"%>
<jsp:include page="header.jsp" />

 <script>
    function validateForm() {
        if (confirm("Are your sure ?") == true) {
            return true;
        } else {
            return false
        }
    }
</script>

<section>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h1 text-center> ADD NEW OBJECT </h1>
                
                <form method="POST" action="Manage" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="Name">Name</label>
                        <input type="text" class="form-control" id="Name" placeholder="Name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="Price">Price</label>
                        <input type="number" class="form-control" id="Price" placeholder="Price" name="price" required>
                    </div>
                    <div class="form-group">
                        <label for="Description">Description</label>
                        <input type="text" class="form-control" id="Description" placeholder="Description" name="description" required>
                    </div>
                    <div class="form-group">
                        <label for="Picture">Picture</label>
                        <input type="file" name="file" id="file" required /> <br/>
                    </div>
                    <input type="submit" value="Upload" name="upload" id="upload" class="btn btn-default"/>
                </form>
            </div>
            
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h2 class="text-center"> Delete Object </h2>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <tr>
                            <th> Name </th>
                            <th> Price </th> 
                            <th> Description </th>
                            <th> Delete </th>
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
                                    <form method="POST" action="Delete" onsubmit="return validateForm()" name="myForm">
                                       <input type="hidden" name="param" value=<%= supobject.getId() %> /> 
                                       <input type="submit" value="Delete" name="upload" class="btn btn-default"/>
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

