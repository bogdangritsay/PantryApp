<#import "parts/common.ftl" as c>

<@c.page>
<div class="form group mt-3">
    <#assign currencyType = '₴'>
    <div class="form group col-md-6">
        <h4>Your order History</h4>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Total Order Price</th>
            <th scope="col">Date Of Submit</th>
            <th scope="col">Delivery Date</th>
        </tr>
        </thead>
        <tbody>
        <#list orders as order>
        <tr>
            <td>${order?index+1}</td>
            <td>${order.orderName}</td>
            <td>${currencyType} ${(order.totalorderPrice)?string(",##0.00")}</td>
            <td>${order.dateofSubmit}</td>
            <td>${order.deliveryDate}</td>
        </tr>
        </#list>
        </tbody>
    </table>

    <p align="left">
    <h6 style="font-weight: bold"> TOTAL: <#if order?has_content> ${currencyType} ${(order.totalorderPrice)?string(",##0.00")} <#else>${currencyType} 0,00 </#if> UAH</h6>
    </p>
</div>
</@c.page>