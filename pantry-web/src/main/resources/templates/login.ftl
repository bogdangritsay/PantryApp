<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pantry</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row no-gutter">
        <!-- The image half -->
        <div class="col-md-6 d-none d-md-flex" style="background-image: url('images/mainphoto.jpg'); background-size: cover; background-position:  center center;"></div>
        <!-- The content half -->
        <div class="col-md-6 bg-light">
            <div class="login d-flex align-items-center py-5" style="min-height: 100vh;">

                <!-- Demo content-->
                <div class="container">
                    <div class="row">
                        <div class="col-lg-10 col-xl-7 mx-auto">
                            <h3 class="display-4">Login to Pantry!</h3>
                            <p class="text-muted mb-4">Enter your username and password.</p>
                            <form action="/login" method="post">
                                <div class="form-group mb-3">
                                    <input type="text" name="username" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Username" required autofocus/>
                                </div>
                                <div class="form-group mb-3">
                                    <input type="password" name="password" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Password" required />
                                </div>
                                <div class="custom-control custom-checkbox mb-3">
                                    <input id="rememberMe" type="checkbox" checked class="custom-control-input" name="remember-user">
                                    <label for="rememberMe" class="custom-control-label">Remember Me</label>
                                </div>
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <button class="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm" type="submit">Sign In</button>
                                <a class="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm" href="/registration">Sign Up</a>

                                <div class="text-center d-flex justify-content-between mt-4">
                                    <p class="font-italic text-muted"><u>Pantry Helper</u></p>
                                </div>

                            </form>
                        </div>
                    </div>
                </div><!-- End -->

            </div>
        </div><!-- End -->

    </div>
</div>
</body>
</html>