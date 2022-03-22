<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>

    <div class="margin-3"></div>

    <div class="wrapper">
        <div class="container">
            <div class="container mt-5 mb-5">
                <h1>Adding New Product to Catalog</h1>
                <hr>
                <div class="margin-3"></div>
                <div>
                    <form method="post">
                        <input type="text" name="name" placeholder="Name: " class="form-control" required maxlength="255"><br>
                        <select name="category" class="form-control" required>
                            <option disabled selected>Choose Category</option>
                            <#list categories as category>
                            <option value="${category.id}">${category.name}</option>
                            </#list>>
                        </select><br>

                        <input type="text" name="description" placeholder="Description: " class="form-control"><br>
                        <input type="number" step="0.01" name="itemPrice" placeholder="Item Price: " class="form-control" required><br>
                        <select name="store" class="form-control" required><br>
                            <!-- get from databases -->
                            <option disabled selected>Choose Store</option>
                            <#list stores as store>
                                <option value="${store.id}">${store.name}</option>
                            </#list>
                        </select><br>
                        <input type="text" name="link" placeholder="Link to Product in Shop: " class="form-control" required maxlength="255"><br>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit" class="btn btn-success">Create</button>
                    </form>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>


</@c.page>