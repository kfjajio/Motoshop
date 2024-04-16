<!DOCTYPE html>
<html>
<head>
    <title>MOTO-SHOP</title>
</head>
<body>
<h1>MOTO-SHOP</h1><hr>
<h3>Подробная информация о пользователе ${user.name}</h3>
<b>Номер телефона :</b> ${user.phoneNumber}<br>
<b>Email :</b> ${user.email}<br>
<h4>Товары пользователя ${user.name}</h4>
<#list moto as moto >
    <div>
        <p><b>${moto.title}</b> ${moto.price} доллоров | <a href="/moto/${moto.id}">Подробнее...</a></p>
    </div>
    <#else>
    <h3>Мотоциклов нет</h3>
</#list>
<#if errorMessage??>
    <h2 style="color:red">${errorMessage}</h2>
</#if>
</body>
</html>