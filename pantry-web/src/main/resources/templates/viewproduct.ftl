<#import "parts/common.ftl" as c>

<@c.page>
<h1>View Product</h1>
<a href="/currentorder">Back to Order </a>
<br/><br/>
<#if product??>
<#-- move tthus one some in part folder -->
<#assign currencyType = '₴'>
<table border="0">
    <tr>
        <td>ID</td>
        <td>:</td>
        <td>${product.id}</td>
    </tr>
    <tr>
        <td>Title</td>
        <td>:</td>
        <td>${product.name}</td>
    </tr>
    <tr>
        <td>Price</td>
        <td>:</td>
        <td>${currencyType} ${(product.orderPrice)?string(",##0.00")}</td>
    </tr>
    <tr>
        <td>Amount</td>
        <td>:</td>
        <#if isPantry>
            <td>${product.pantryAmount}</td>
        <#else>
            <td>${product.orderAmount}</td>
        </#if>
    </tr>
    </tr>
    <tr>
        <td>Created On</td>
        <td>:</td>
        <td>${product.prevOrderTime}</td>
    </tr>
</table>
<br/><br/>
<#if allowDelete>
<form action="$/currentorder/delete/${product.id}" method="POST">
    Delete this product? <input type="submit" value="Yes" />
</form>
<#else>
</#if>
</#if>
<#if errorMessage?has_content>
<div class="error">${errorMessage}</div>
</#if>
</@c.page>