<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>

<@spring.message "message.settings"/>
<div class="form group mt-3">
    <form  method="get" action="/settings">
        <ul class="list-group">
            <li class="list-group-item disabled">${user.lastName}, ${user.firstName} <#if user.middleName?? > ${user.middleName}</#if></li>
            <li class="list-group-item">${user.address}</li>
            <li class="list-group-item">${user.defaultSite}</li>
            <li class="list-group-item">${user.email}</li>
            <li class="list-group-item">${user.phone}</li>
            <a href="/settingEdit" class="list-group-item list-group-item-action">Edit</a>
        </ul>
    </form>
</div>

</@c.page>