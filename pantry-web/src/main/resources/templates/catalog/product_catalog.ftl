<#import "../parts/common.ftl" as c>

<@c.page>
    <div class="form row">
        <div class="form group col-md-6">
            <form method="get" action="/сatalog" class="form-inline" >
                <input type="text" name="filter" value="${filter?ifExists}" placeholder="Search by Name">
                <button type="submit" class="btn btn-primary ml-2">Find Product</button>
            </form>
        </div>
    </div>

    <div class="margin-3"></div>

    <div class="wrapper">
        <div class="container">
            <div class="container mt-5 mb-5">
                <div class="col-md-12  align-middle">
                    <h1 class="float-start">Product  Catalog</h1>
                    <a href="catalog/add-product" class="btn btn-success float-end">Add New Product to Catalog</a>
                </div>
                <div class="margin-6"></div>

            <div class="margin-3"></div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Add to Cart (not working now)</th>
                </tr>
                </thead>
                <tbody>
                <#list products as product>
                    <tr>
                        <td>${product?index+1}</td>
                        <td><a href="${product.linkToProduct}" target="_blank">${product.name}</a></td>
                        <td>${product.itemPrice}</td>
                        <td>
                            <form action="/catalog-add/" method="post" >
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="number" name="id" value="${product.id}" hidden/>
                                <input type="submit" value="Add to Current Order" />
                            </form>
                        </td>
                    </tr>
                <#else>
                    No Product
                </#list>
                </tbody>
            </table>
            </div>
        </div>
    </div>
</@c.page>