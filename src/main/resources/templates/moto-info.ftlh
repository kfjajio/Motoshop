<!DOCTYPE html>
<html>
<head>
    <title>MOTO-SHOP</title>
</head>
<body>
<h1>Moto-shop!</h1><hr>
<h4>Подробная информация о мотоцикле</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Название мотоцикла: </b>${moto.title}<br/>
<b>Описание мотоцикла: </b>${moto.description}<br/>
<b>Цена: </b>${moto.price}<br/>
<b>Город: </b>${moto.city}<br/>
<b>Автор: </b> <a href = "/user/${moto.user.id}">${moto.user.name}</a><br/>
</body>
</html>