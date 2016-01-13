<%-- 
    Document   : EditProfil
    Created on : 11 déc. 2015, 12:51:09
    Author     : oumartraore
--%>

<%
    String password = request.getAttribute("passwordE").toString();
    String email = request.getAttribute("emailE").toString();
    String codePostale = request.getAttribute("codePostaleE").toString();
%>


<jsp:include page="header.jsp" />

<script>
    function validateForm() {
        var password = document.forms["myForm"]["password"].value;
        var cpassword = document.forms["myForm"]["cpassword"].value;

        if (password != cpassword) {
            document.write("Your Password don't Match");
            return false;
        }
    }
</script>

<section>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h1 text-center> Edit Profil </h1>

                <form method="POST" action="Edit"  onsubmit="return validateForm()" name="myForm">
                    <div class="form-group">
                        <label for="Firstname">Username</label>
                        <input type="text" class="form-control" id="Firstname" placeholder="Firstname" name="firstname" required>
                    </div>
                    <div class="form-group">
                        <label for="Lastname">Username</label>
                        <input type="text" class="form-control" id="Lastname" placeholder="Lastname" name="lastname" required>
                    </div>
                    <div class="form-group">
                        <label for="CodePostale">Code Postale</label>
                        <input type="number" class="form-control" id="CodePostale" value=<%= codePostale %> name="codePostale" required>
                    </div>
                    <div class="form-group">
                        <label for="Email">Email address</label>
                        <input type="email" class="form-control" id="Email" value=<%= email %> name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="Password">Password</label>
                        <input type="password" class="form-control" id="Password" value=<%= password %> name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="CPassword">Confirm Password</label>
                        <input type="password" class="form-control" id="cPassword" value=<%= password %> required/>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </div>
</section>

    </body>
</html>
