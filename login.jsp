<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/AlertifyJS/1.13.1/css/alertify.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/AlertifyJS/1.13.1/css/themes/default.min.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/AlertifyJS/1.13.1/alertify.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/alertifyjs@1.13.1/dist/js/alertify.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/alertifyjs@1.13.1/dist/css/alertify.css" />
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script type="text/javascript">
            alertify.set('notifier', 'position', 'top-center');

            function valid() {
                var name = document.forms["loginform"]["username"].value;
                var password = document.forms["loginform"]["password"].value;

                if (name == "")
                {
                    alertify.error("Empty name, please enter the name");
                    return false;
                }
                if (password == "")
                {
                    alertify.error("Empty password, please enter the password");
                    return false;
                }

            }
        </script>



        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="form">
            <h2>Please fill your details for login</h2>
            <form method="POST" action="LoginServlet" name="loginform" onsubmit="return valid()">
                <div>
                    <label for="username" class="input-icon">
                        <i class="fa fa-user"></i>
                        <input type="text" class="input_form" name="username" id="username" placeholder="username">
                    </label>
                </div>
                <br/><br/><br/>
                <div>
                    <label for="password" class="input-icon">
                        <i class="fa fa-lock"></i>
                        <input type="password" class="input_form" name="password" id="password" placeholder="password">
                    </label>
                </div>
                <br/><br/><br/>
                <div>
                    <center><input type="submit" name="submit" class="btn_submit" value="SIGN IN"></center>
                </div>
                <br/><br/>
                <div>
                    Don't have an account? Visit <a href="#" onclick="return no()"> Here </a> to register
                </div>
            </form>
        </div>
        <script>
            function no()
            {
                swal({
                    title: "Please note that",
                    text: "Only admins can register the users!!",
                    icon: "warning",
                    buttons: {
                        confirm: {
                            text: "OK",
                            value: true,
                            visible: true,
                            className: "btn btn-danger",
                            closeModal: true
                        }
                    },
                });
                return false;
            }

        </script>  
    </body>
</html>
