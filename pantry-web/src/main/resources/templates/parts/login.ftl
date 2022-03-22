<#macro login path isRegisterForm>
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
                            <form action="${path}" method="post">
                                <div class="form-group mb-3">
                                    <input type="text" name="username" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Username" required autofocus/>
                                </div>
                                <div class="form-group mb-3">
                                    <input type="password" name="password" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Password" required />
                                </div>
                                <div class="custom-control custom-checkbox mb-3">
                                    <input id="customCheck1" type="checkbox" checked class="custom-control-input">
                                    <label for="customCheck1" class="custom-control-label">Remember password</label>
                                </div>
                                <#if isRegisterForm>
                                    <div class="form-group mb-3">
                                    <input type="password" name="password2" class="form-control rounded-pill border-0 shadow-sm px-4" placeholder="Confirm Password" />
                                </div>
                                </#if>
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                              <#--  <#if !isRegisterForm><a href="/registration">Add new user</a></#if>-->
                                <button class="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
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


</#macro>

<#macro loginbtn>
    <form class="d-flex" action="/login" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sign In</button>
    </form>
</#macro>

