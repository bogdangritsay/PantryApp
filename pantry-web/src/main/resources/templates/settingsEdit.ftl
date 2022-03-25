<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>

<@spring.message "message.settings"/>
<div class="form group mt-3">
    <form  method="post">
        <input type="text" name="address" placeholder="Address"  value="${user.address}" required>
        <input type="text" name="email" placeholder="Email" value="${user.email}" required>
        <input type="text" name="defaultSite" placeholder="Site" value="${user.defaultSite}">
        <input type="text" name="phone" placeholder="Phone number" value="${user.phone}" required>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Save</button>
    </form>
</div>

</@c.page>