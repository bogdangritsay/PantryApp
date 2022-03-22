<#import "parts/common.ftl" as c>

<@c.page>
<h1>Edit a Product:</h1>
<a href="/currentorder">Back to Order</a>
<br/><br/>

<#assign urlAction>${'/currentorder/edit/'+ product.id}</#assign>
<#assign submitTitle>Update</#assign>
<form action="${urlAction}" name="note" method="POST">
    <table border="0">
    <tr>
        <td>ID</td>
        <td>:</td>
        <td>${product.id}</td>
    </tr>
    <tr>
        <td>Title</td>
        <td>:</td>
        <td><input type="text" name="title" value=${product.name} /></td>
    </tr>
    <tr>
        <td>Price</td>
        <td>:</td>
        <td><textarea name="orderPrice" rows="4"  cols="50">${(product.orderPrice)!""}</textarea></td>
    </tr>
    <tr>
        <td>Order Amount</td>
        <td>:</td>
        <td><textarea name="orderAmount" rows="4" cols="50">${(product.orderAmount)!""}</textarea></td>
    </tr>
    <tr>
        <td>added On</td>
        <td>:</td>
        <td>${product.currentOrderTime}</td>
    </tr>
</table>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Save</button>
</form>

<br/>
<!-- Check if errorMessage is not null and not empty -->
<#if errorMessage?has_content>
<div class="error">${errorMessage}</div>
</#if>
</@c.page>