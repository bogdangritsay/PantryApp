<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form row">
        <div class="form group col-md-6">
            <form method="get" action="/main" class="form-inline" >
                <input type="text" name="filter" value="${filter?ifExists}" placeholder="Search by Name">
                <button type="submit" class="btn btn-primary ml-2">Find Product</button>
            </form>
        </div>
    </div>
    <div class="form group mt-3">
        <form method="post">
            <input type="text" name="name" placeholder="Product Name: ">
            <input type="text" name="amount" placeholder="amount: ">
            <input type="number" name="pantryAmount" placeholder=" pantry amount: ">
            <input type=number name="price" step=0.01 placeholder="price: ">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add</button>
        </form>
    </div>
    <div class="form row mt-3">
    </div>
    <table class="table">
        <thead>
        <#assign currencyType = '₴'>
        <#assign daysRow = 'days'>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Amount</th>
            <th scope="col">Distance</th>
            <th scope="col">Day Left to Order</th>
            <th scope="col">Price</th>
            <th scope="col">Owner</th>
        </tr>
        </thead>
        <tbody>
        <#if products?hasContent>
        <#list products as product>
            <tr>
                <td>${product?index+1}</td>
                <td><a href="${'main/product/' + product.id}">${product.name}</a></td>
                <td>${product.pantryAmount}</td>
                <td>${product.distanceDays} ${daysRow}</td>
                <td>${product.pantryDayLeft} ${daysRow}</td>
                <td>${currencyType} ${(product.price)?string(",##0.00")}</td>
                <td>${product.ownerName}</td>
            </tr>
        </#list>
        <#else>
            <tr> <td colspan="7">No Products</td></tr>
        </#if>
        </tbody>
    </table>
</@c.page>