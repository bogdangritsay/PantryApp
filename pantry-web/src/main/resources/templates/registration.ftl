<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pantry</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/passwordConfirmation.js" type="application/javascript"></script>
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
                            <h3 class="display-4">Create a new account</h3>
                            <p class="text-muted mb-4">Please fill in the fields below</p>
                            <form action="/registration" method="post">
                                <div class="form-group mb-3">
                                    <input type="email" name="email" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Email" required autofocus/>
                                </div>
                                <div class="form-group mb-3">
                                    <input type="text" name="lastName" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Last Name" required />
                                </div>
                                <div class="form-group mb-3">
                                    <input type="text" name="firstName" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="First Name" required />
                                </div>
                                <div class="form-group mb-3">
                                    <input type="text" name="middleName" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Middle Name" />
                                </div>
                                <div class="form-group mb-3">
                                    <input type="text" name="phone" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Phone number" required />
                                </div>
                                <div class="form-group mb-3">
                                    <input type="password" name="password" id="password" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Password" required />
                                </div>
                                <div class="form-group mb-3">
                                    <input type="password" name="passwordConfirm" id="passwordConfirm" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Password Confirmation" required />
                                </div>
                                <div id="errorBlock" class="alert alert-danger" style="display: none"></div>

                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <button class="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm" id="sbmBtn" type="submit" disabled>Sign Up</button>

                                <div class="text-center d-flex justify-content-between mt-4">
                                    <p class="font-italic text-muted"><u>Pantry Helper</u></p>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>
</body>
</html>