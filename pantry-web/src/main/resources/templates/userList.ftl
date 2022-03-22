<#import "parts/common.ftl" as c>

<@c.page>
<div>Список пользователей</div>

<table>
    <thead>
    <th>Name</th>
    <th>Role</th>
    <th></th>
    </thead>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">edit</a></td>
        </tr>
    </#list>
</table>

</@c.page>