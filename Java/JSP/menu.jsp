<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        .menu{
         background-color: lightslategray;

        }
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li,#search,#search2 {
            float: left;
        }
        #search,#search2{
            display: block;
            color: black;
            padding-top: 14px;
            text-align: center;
            text-decoration: none;
        }
        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover {
            background-color: #111;
        }
        hr{
            color:white;

        }
    </style>
</head>
<body>

<ul class="menu">
    <li><a href="#">Assignemt Store</a></li>
    <li><a href="products.jsp">Product</a></li>
    <li><a href="basket.jsp">Basket</a></li>
    <form onsubmit="return ValidateForm(this)">
        <li><input id="search" type="text" size="32" name="search" placeholder="Search By Artist Name"> </li>
        <li><input type="submit" size="32" name="search" value="Find By Artist" /></li>
    </form>
    <form onsubmit="return ValidateForm(this)">
        <li><input id="search2" type="text" size="32" name="search" placeholder="Search By Title"> </li>
        <li><input type="submit" size="32" name="search" value="Find By Title" /></li>
    </form>


</ul>
<hr>

<script>


    function ValidateForm(form)
    {
        if(form.name.value == "") {
            alert("Enter Your Name !");
            return false;
        }
        // regular expression to match only alphanumeric characters and spaces
        var reg = /^[\w ]+$/;
        if(!reg.test(form.name.value)) {
            alert("Enter valid name ");
            return false;
        }
        return true;
    }

</script>


</body>
</html>