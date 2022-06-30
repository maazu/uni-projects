<%@ page import="shop.Product"%>

<jsp:useBean id='db'
             scope='session'
             class='shop.ShopDB' />

<html>
<head>
<title>View Product </title>
    <link rel="stylesheet" type="text/css" href="css/Product_1706038.css" />
</head>
<body>

<jsp:include page="menu.jsp"/>
<%
    String pid = request.getParameter("pid");
    Product product = db.getProduct(pid);
    // out.println("pid = " + pid);
    if (product == null) {
        // do something sensible!!!
        out.println( product );
    }
    else {
        %>
        <div align="center">
            <h2> <%= product.title %>  by <%= product.artist %> </h2>
            <img style="height: 350px;width: 350px;" id="image-pic" src="<%= product.fullimage %>" />
            <legend> Description </legend>
            <p> <%= product.description %> </p>
            <legend>Price </legend>
            <p> <%= product.formatPrice() %> </p>
            <p><img  src="images/basket-icon.png"> <a href='<%="basket.jsp?addItem="+product.PID%>'> Add To Basket </a></p>

        </div>
        <%
    }
%>
</body>
</html>
