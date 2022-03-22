<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>

<@spring.message "message.settings"/>
<div class="form group mt-3">
    <form  method="post">
        <input type="text" name="address" placeholder="Address: ">
        <input type="text" name="email" placeholder="email: ">
        <input type="text" name="defaultSite" placeholder="Site: ">
        <input type="text" name="phone" placeholder="phone: ">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Save</button>
    </form>
</div>

</@c.page>