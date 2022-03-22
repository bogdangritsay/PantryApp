<#import "parts/common.ftl" as c>

<@c.page>
<div class="form group mt-3">
    <#assign currencyType = '₴'>
    <#if orders?has_content>
        <h2> Your In Delivery orders </h2>
        <#list orders as order>
            <div class="card" style="margin-top: 20px">
                <div class="card-body">
                    <div class="float-start">
                        <h5> Order ${order.getOrderName()}</h5>
                        <p>Shipping Address: <br> ${shippingAddress}</p>
                    </div>
                    <div class="float-end">
                        <h6 class="card-subtitle mb-2 text-muted">${order.getDateOfSubmitInSimpleFormat()}</h6>
                        Total
                        <h2><#if order?has_content>${(order.totalOrderPrice)?string(",##0.00")} ${currencyType} <#else> 0,00${currencyType} </#if></h2>
                    </div>
                </div>
                <div class="card-body">
                    <div class="float-start">
                        <form  action ="/delivered" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="hidden" name="deliveredOrderId" value="${order.id}">
                            <button type="submit" class="btn btn-success">Delivered</button>
                        </form>
                        <div class="collapse" id="collapseOrder_${order.getId()}" style="margin-top: 10px">
                            <div class="card card-body">
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
                                    <#list order.getOrderDetails() as item>
                                        <tr>
                                            <td>${item?index+1}</td>
                                            <td><a href="${'currentorder/product/' + item.product.id}">${item.product.name}</a></td>
                                            <td>${item.orderAmount}</td>
                                            <td>${currencyType} ${(item.product.itemPrice)?string(",##0.00")}</td>
                                            <td>${currencyType} ${(item.totalPrice)?string(",##0.00")}</td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="float-end" style="margin-bottom: 15px">
                        <a class="card-link" data-bs-toggle="collapse" href="#collapseOrder_${order.getId()}" role="button" aria-expanded="false" aria-controls="collapseOrder_${order.getId()}">
                            Show details
                        </a>
                        </p>
                    </div>

                </div>

            </div>

        </#list>
    <#else> ${errorMessage} </#if>
</div>
</@c.page>