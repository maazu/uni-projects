<%@ page import="shop.Product"%>

<jsp:useBean id='db'
             scope='session'
             class='shop.ShopDB' />

<html>
<head>
<title>My Store </title>
    <link rel="stylesheet" type="text/css" href="css/Product_1706038.css">
</head>
<body>

<jsp:include page="menu.jsp"/>

<h2> Available Products </h2>
<div align="center">
<table id="products ">
<tr>
<th> Title </th>
    <th> Price </th>
    <th> Picture </th>
</tr>
<%
    for (Product product : db.getAllProducts() ) {
        // now use HTML literals to create the table
        // with JSP expressions to include the live data
        // but this page is unfinished - the thumbnail
        // needs a hyperlink to the product description,
        // and there should also be a way of selecting
        // pictures from a particular artist
        %>
        <tr>
             <td> <%= product.title %> </td>
             <td> <%= product.formatPrice() %> </td>
             <td> <a href = '<%="viewProduct.jsp?pid="+product.PID%>'> <img id="image-pic-pro" style="height:70px;width: 70px; " src="<%= product.thumbnail %>"/> </a> </td>
        </tr>
        <%
    }
 %>
 </table>
</div>
</body>
</html>
