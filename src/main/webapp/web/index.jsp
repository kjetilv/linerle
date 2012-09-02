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
<script type="text/javascript" src="/linerle/scripts/linerle.js"></script>

<h1>Welcome To Struts 2!</h1>

<script type="text/javascript">
    <s:property value="script"/>
</script>

<p>

<table id="books">

</table>

<p>
<h2>Add book</h2>
<label for="title">Title
    <input id="title" type="text" size="30">
</label>
<label for="year">Year
    <input id="year" type="text" size="4">
</label>
<h3>Author</h3>
<label for="author-last">Last name
    <input id="author-last" type="text" size="30">
</label>
<label for="author-first">First name
    <input id="author-first" type="text" size="30">
</label>
<button onclick="addCurrentBook();">
    Add
</button>

<script type="text/javascript">
    function listBook(book) {
        $('#books tr:last').after('<tr>' +
                                  '  <td>' + book.title + '</td>' +
                                  '  <td>' + book.author.lastName + ', ' + book.author.firstName + '</td>' +
                                  '  <td>' + book.year.year + '</td>' +
                                  '</tr>');
    }

    function clearTable() {
        $('#books').html('<thead>' +
                         '  <tr>' +
                         '    <td>Title</td>' +
                         '    <td>Author</td>' +
                         '  <td>Year</td>' +
                         '</thead>' +
                         '<tbody>' +
                         '  <tr></tr>' +
                         '</tbody>');
    }
    function refreshBooks() {
        getBooks('', function(books) {
            clearTable();
            for (var i = 0; i < books.length; i++) {
                listBook(books[i]);
            }
        });
    }

    function addCurrentBook() {
        var title = $('#title').val();
        var year = $('#year').val();
        var firstName = $('#author-first').val();
        var lastName = $('#author-last').val();

        var book = {
            title: title,
            year: {
                year: year
            },
            author: {
                firstName: firstName,
                lastName: lastName
            }
        };

        addBook(book, function(stored) {
            listBook(stored);
        });
    }

    refreshBooks();
</script>
<p/>

<button onclick="refreshBooks()" name="button1" >Refresh books</button>

</body>
</html>