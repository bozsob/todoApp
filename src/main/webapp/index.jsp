<%--
  Created by IntelliJ IDEA.
  User: trixi
  Date: 2017.05.11.
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="style/style.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>ToDoApp</title>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">MyToDoList</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
        </ul>
    </div>
</nav>

<div class="container">

    <div class="jumbotron">
        <h1 id="mainheader" class="well">My To Do List</h1>
        <button id="allButton" class="btn btn-default" onclick="loadTodos('all')">All</button>
        <button id="inProgress" class="btn btn-default" onclick="loadTodos('inprogress')">In Progress</button>
        <button id="completedButton" class="btn btn-default" onclick="loadTodos('completed')">Completed</button>

        <div id="todos"></div>

        <div class="myTodos">
            <input type="text" class="well" name="todo" id="newTodo" autofocus>
            <input type="submit" id="addButton" class="btn btn-primary" value="Add">
        </div>
    </div>

</div>

</div>

</body>
<script src="scripts/todos.js"></script>
</html>

