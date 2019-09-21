<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
</head>
<body>
    <form action="addAlien" method="post">
        Enter your id : <input type="text" name="id"><br>
        Enter your name : <input type="text" name="name"><br>
        <input type="submit"/>
    </form>

    <hr>
    <form action="getAlien" method="get">
        Enter your id : <input type="text" name="id2"><br>
        <input type="submit"/>
    </form>

    <hr>
    <form action="getAlienByName" method="get">
        Enter your name : <input type="text" name="name2"><br>
        <input type="submit"/>
    </form>

</body>
</html>