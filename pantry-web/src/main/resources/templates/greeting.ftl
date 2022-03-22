<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<#include "parts/security.ftl">

<@c.page>
<h5>Hello, <#if  user?has_content>${name}<#else> Guest </#if></h5>
<@spring.message "message.greeting"/>

<div>This application will help you with ordering household goods and household chemicals for regular use!</div>
    <img src="/images/mainphoto.jpg" class="img-fluid" alt="Responsive image">
</@c.page>