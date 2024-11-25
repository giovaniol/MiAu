<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Home</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<div class="container mt-5">
    <c:choose>

        <c:when test="${not empty sessionScope.user}">
            <div class="text-center">
                <h1>Bem-vindo</h1>
                <img src="https://recreio.com.br/media/uploads/2024/02/relampago-mcqueen_capa.jpg" alt="KATCHAAAAUU!!!" class="img-thumbnail" style="max-width: 400px;">
            </div>
        </c:when>

    </c:choose>
</div>

</body>
</html>
