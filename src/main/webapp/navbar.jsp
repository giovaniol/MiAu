    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/custom.css">
        <title>Home</title>
    </head>
    <body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="home.jsp">MiAuDote</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <c:choose>
                                <c:when test="${not empty sessionScope.user and not empty sessionScope.user.nome}">
                                    ${sessionScope.user.nome}
                                </c:when>
                                <c:otherwise>
                                    Conta
                                </c:otherwise>
                            </c:choose>
                        </a>
                        <ul class="dropdown-menu">
                            <c:choose>
                                <c:when test="${not empty sessionScope.user}">
                                    <li><a class="dropdown-item" href="logout">Sair</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a class="dropdown-item" href="login.jsp">Login</a></li>
                                    <li><a class="dropdown-item" href="user-register.jsp">Cadastrar</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </li>

                </ul>
                <button id="switchTheme" class="btn btn-dark">Trocar Tema</button>
            </div>
        </div>
    </nav>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
