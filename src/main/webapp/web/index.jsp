<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>linerle</title>
</head>
<body>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.js"></script>
<script type="text/javascript" src="http://ajax.cdnjs.com/ajax/libs/json2/20110223/json2.js"></script>

<h1>Welcome To Struts 2!</h1>

<script type="text/javascript">
    <s:property value="script"/>
</script>

<p>
    <s:property value="name"/>
</p>

<button onclick="getBooks('David')" name="button1" value="ButtonValue">ButtonBody</button>

</body>
</html>