<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>

<@spring.message "message.settings"/>
<div class="form group mt-3">
    <form  method="get" action="/settings">
        <ul class="list-group">
            <li class="list-group-item disabled">${user.username}</li>
            <li class="list-group-item">${setting.address}</li>
            <li class="list-group-item">${setting.defaultSite}</li>
            <li class="list-group-item">${setting.email}</li>
            <li class="list-group-item">${setting.phone}</li>
            <a href="/settingEdit" class="list-group-item list-group-item-action">Edit</a>
        </ul>
    </form>
</div>

</@c.page>