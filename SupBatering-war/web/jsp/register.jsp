<%-- 
    Document   : register
    Created on : 9 déc. 2015, 15:58:11
    Author     : oumartraore
--%>

<jsp:include page="header.jsp" />
<section>
    
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
    
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h1 text-center> Register Area </h1>

                <p id="error"></p>
                
                <form method="POST" action="Register" onsubmit="return validateForm()" name="myForm">
                    <div class="form-group">
                        <label for="Username">Username</label>
                        <input type="text" class="form-control" id="Username" placeholder="Username" name="username" required>
                    </div>
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
                        <input type="number" class="form-control" id="CodePostale" placeholder="Code Postale" name="codePostale" required>
                    </div>
                    <div class="form-group">
                        <label for="Email">Email address</label>
                        <input type="email" class="form-control" id="Email" placeholder="Email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="Password">Password</label>
                        <input type="password" class="form-control" id="Password" placeholder="Password" name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="CPassword">Confirm Password</label>
                        <input type="password" class="form-control" id="CPassword" placeholder="Confirm Password" name="cpassword" required>
                    </div>

                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </div>
</section>
        
<jsp:include page="footer.jsp" />
