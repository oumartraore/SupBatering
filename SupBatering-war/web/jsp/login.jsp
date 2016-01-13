<%-- 
    Document   : login
    Created on : 9 déc. 2015, 15:57:58
    Author     : oumartraore
--%>

<jsp:include page="header.jsp" />

<%
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("AuthenticationOumar")) {
                response.sendRedirect("Login");
            }
        }
    }
%>

<section>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h1 text-center> Login Area </h1>

                <form method="POST" action="Login">
                    <div class="form-group">
                        <label for="Username">Username</label>
                        <input type="text" class="form-control" id="Username" placeholder="Username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="Password">Password</label>
                        <input type="password" class="form-control" id="Password" placeholder="Password" name="password" required>
                    </div>

                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp" />