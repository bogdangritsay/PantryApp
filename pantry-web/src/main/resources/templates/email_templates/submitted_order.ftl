<#import "../parts/common.ftl" as c>

<#assign currencyType = '₴'>

<div style="font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c">

    <span style="display:none;font-size:1px;color:#fff;max-height:0"></span>

    <table role="presentation" width="100%" style="border-collapse:collapse;min-width:100%;width:100%!important" cellpadding="0" cellspacing="0" border="0">
        <tbody><tr>
            <td width="100%" height="53" bgcolor="#0b0c0c">

                <table role="presentation" width="100%" style="border-collapse:collapse;max-width:580px" cellpadding="0" cellspacing="0" border="0" align="center">
                    <tbody><tr>
                        <td width="70" bgcolor="#0b0c0c" valign="middle">
                            <table role="presentation" cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse">
                                <tbody><tr>
                                    <td style="padding-left:10px">

                                    </td>
                                    <td style="font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px">
                                        <span style="font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block">${title}</span>
                                    </td>
                                </tr>
                                </tbody></table>
                            </a>
                        </td>
                    </tr>
                    </tbody></table>

            </td>
        </tr>
        </tbody></table>
    <table role="presentation" class="m_-6186904992287805515content" align="center" cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;max-width:580px;width:100%!important" width="100%">
        <tbody><tr>
            <td width="10" height="10" valign="middle"></td>
            <td>

                <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse">
                    <tbody><tr>
                        <td bgcolor="#1D70B8" width="100%" height="10"></td>
                    </tr>
                    </tbody></table>

            </td>
            <td width="10" valign="middle" height="10"></td>
        </tr>
        </tbody></table>



    <table role="presentation" class="m_-6186904992287805515content" align="center" cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;max-width:580px;width:100%!important" width="100%">
        <tbody><tr>
            <td height="30"><br></td>
        </tr>
        <tr>
            <td width="10" valign="middle"><br></td>
            <td style="font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px">

                <p style="Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c">Hi, ${name}!</p>
                <p style="Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c"> Thank you for order! You can find information about order below or in Personal Cabinet of PantryApp.</p>
                <p>See you soon</p>
                <br>
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
                            <td>${item.product.name}</td>
                            <td>${item.orderAmount}</td>
                            <td>${currencyType} ${(item.product.itemPrice)?string(",##0.00")}</td>
                            <td>${currencyType} ${(item.totalPrice)?string(",##0.00")}</td>
                        </tr>
                    </#list>
                    </tbody>
                    <div class="form row">
                        <p align="left" >
                        <p class="font-weight-bold">TOTAL: </p>
                        <p class="font-weight-bold">${currencyType} ${((order.totalOrderPrice)?string(",##0.00"))!"0.00"} UAH </p>
                    </div>
                </table>
            </td>
            <td width="10" valign="middle"><br></td>
        </tr>
        <tr>

            <td height="30"><br></td>
        </tr>

        </tbody></table>

    <div class="yj6qo"></div><div class="adL">

    </div></div>


