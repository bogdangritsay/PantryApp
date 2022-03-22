<#include "security.ftl">
<#import "login.ftl" as l>


<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">PantryApp</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#pantrynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <#if user??>
        <div class="collapse navbar-collapse" id="pantrynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/catalog">Catalog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/main">Balance of Goods</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/currentorder">Current Order</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/indelivery">In Delivery</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/orderHistory">History</a>
                </li>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/user">Users</a>
                    </li>
                </#if>
            </ul>



            <ul class="nav navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                        <img src="/images/default_avatar.png" alt="Avatar Logo" style="width:40px; margin-right: 5px;" class="rounded-pill">
                        ${name}
                    </a>
                    <div class="dropdown-menu dropdown-menu-end">
                        <a href="/user/profile" class="dropdown-item">Profile</a>
                        <a href="/settings" class="dropdown-item">Settings</a>
                        <div class="dropdown-divider"></div>
                        <form class="d-flex" action="/logout" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button class="dropdown-item" type="submit">Logout</button>
                        </form>
                    </div>
                </li>
            </ul>


        </div>
        <#else>
        <@l.loginbtn />
        </#if>
    </div>
</nav>