<#import "parts/common.ftl" as c>

<@c.page>
    <#assign currencyType = '₴'>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Amount</th>
            <th scope="col">Item Price</th>
            <th scope="col">Total Price</th>
        </tr>
        </thead>
        <tbody>
        <#if items?hasContent>
            <#list items as item>
            <tr>
                <td>${item?index+1}</td>
                <td><a href="${'currentorder/product/' + item.product.id}">${item.product.name}</a></td>
                <td>${item.orderAmount}</td>
                <td>${currencyType} ${(item.product.itemPrice)?string(",##0.00")}</td>
                <td>${currencyType} ${(item.totalPrice)?string(",##0.00")}</td>
                <td>
                    <form  action =/currentorder/delete/${item.id} method="post" name="remove_item">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit" class="btn btn-outline-secondary">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"></path><path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"></path></svg>
                            <span class="visually-hidden">Button</span>
                        </button>
                    </form>
                </td>
            </tr>
            </#list>
        <#else>
            <tr> <td colspan="7">No Products</td></tr>
        </#if>
        </tbody>
    </table>
    <div class="form row">
        <p align="left" >
        <p class="font-weight-bold">TOTAL: </p>
        <p class="font-weight-bold">${currencyType} ${((order.totalOrderPrice)?string(",##0.00"))!"0.00"} UAH </p>
    </div>
    <#if items?hasContent>
        <div class="form row" >
            <form  action ="/submitorder" method="post" onsubmit="validate_form()">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit">Submit Order</button>
            </form>
        </div>
    </#if>
</@c.page>